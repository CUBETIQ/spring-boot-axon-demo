package com.cubetiqs.demo.axon.service

import com.cubetiqs.demo.axon.entity.BankAccount
import com.cubetiqs.demo.axon.query.FindBankAccountQuery
import com.cubetiqs.demo.axon.util.text.formatUuid
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors

@Service
class AccountQueryServiceImpl @Autowired constructor(
    private val queryGateway: QueryGateway,
    private val eventStore: EventStore
) : AccountQueryService {
    override fun findById(accountId: String?): CompletableFuture<BankAccount?> {
        return this.queryGateway.query(
            FindBankAccountQuery(accountId.formatUuid()),
            ResponseTypes.instanceOf(BankAccount::class.java)
        )
    }

    override fun listEventsForAccount(accountId: String?): List<Any?> {
        return this.eventStore
            .readEvents(accountId.formatUuid().toString())
            .asStream()
            .map { it.payload }
            .collect(Collectors.toList());
    }
}