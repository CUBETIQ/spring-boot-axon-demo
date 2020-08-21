package com.cubetiqs.demo.axon

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
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@SpringBootApplication
class AxonApplication

fun main(args: Array<String>) {
    runApplication<AxonApplication>(*args)
}

@RestController
@RequestMapping
class DefaultController {
    @GetMapping
    fun index(): ResponseEntity<Any> {
        return ResponseEntity.ok("ok")
    }
}

@Entity
@Table(name = "bank_accounts")
data class BankAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var owner: String? = null,

    var balance: BigDecimal? = null
) : Serializable

@Aggregate
class BankAccountAggregate(
    @AggregateIdentifier
    private var id: UUID? = null,
    private var balance: BigDecimal? = null,
    private var owner: String? = null
) {
    @CommandHandler
    constructor(command: CreateAccountCommand) {
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

data class CreateAccountCommand(
    @TargetAggregateIdentifier
    val accountId: UUID? = null,
    val initialBalance: BigDecimal? = null,
    val owner: String? = null
)