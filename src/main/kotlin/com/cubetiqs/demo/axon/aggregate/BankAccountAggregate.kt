package com.cubetiqs.demo.axon.aggregate

import com.cubetiqs.demo.axon.command.CreateAccountCommand
import com.cubetiqs.demo.axon.command.CreditMoneyCommand
import com.cubetiqs.demo.axon.command.DebitMoneyCommand
import com.cubetiqs.demo.axon.event.AccountCreatedEvent
import com.cubetiqs.demo.axon.event.MoneyCreditedEvent
import com.cubetiqs.demo.axon.event.MoneyDebitedEvent
import com.cubetiqs.demo.axon.exception.InsufficientBalanceException
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal
import java.util.UUID

@Aggregate
class BankAccountAggregate() {
    @AggregateIdentifier
    private var id: UUID? = null
    private var balance: BigDecimal? = null
    private var owner: String? = null

    @CommandHandler
    constructor(command: CreateAccountCommand) : this() {
        AggregateLifecycle.apply(
            AccountCreatedEvent(
                command.accountId,
                command.initialBalance,
                command.owner
            )
        )
    }

    @EventSourcingHandler
    fun on(event: AccountCreatedEvent) {
        id = event.id
        owner = event.owner
        balance = event.initialBalance
    }

    @CommandHandler
    fun handles(command: CreditMoneyCommand) {
        AggregateLifecycle.apply(
            MoneyCreditedEvent(
                command.accountId,
                command.creditAmount
            )
        )
    }

    @EventSourcingHandler
    fun on(event: MoneyCreditedEvent) {
        balance = balance!!.add(event.creditAmount)
    }

    @CommandHandler
    fun handle(command: DebitMoneyCommand) {
        AggregateLifecycle.apply(
            MoneyDebitedEvent(
                command.accountId,
                command.debitAmount
            )
        )
    }

    @EventSourcingHandler
    @Throws(InsufficientBalanceException::class)
    fun on(event: MoneyDebitedEvent) {
        if (balance!! < event.debitAmount) {
            throw InsufficientBalanceException(event.accountId!!, event.debitAmount!!)
        }
        balance = balance!!.subtract(event.debitAmount)
    }
}