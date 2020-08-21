package com.cubetiqs.demo.axon.projection

import com.cubetiqs.demo.axon.entity.BankAccount
import com.cubetiqs.demo.axon.event.AccountCreatedEvent
import com.cubetiqs.demo.axon.event.MoneyCreditedEvent
import com.cubetiqs.demo.axon.event.MoneyDebitedEvent
import com.cubetiqs.demo.axon.exception.AccountNotFoundException
import com.cubetiqs.demo.axon.repository.BankAccountRepository
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class BankAccountProjection @Autowired constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @EventHandler
    fun on(event: AccountCreatedEvent) {
        log.debug("Handling a Bank Account creation command {}", event.id)
        val bankAccount = BankAccount(
            event.id,
            event.owner,
            event.initialBalance
        )
        this.bankAccountRepository.save(bankAccount)
    }

    @EventHandler
    @Throws(AccountNotFoundException::class)
    fun on(event: MoneyCreditedEvent) {
        log.debug("Handling an Account Credit command {}", event.accountId)
        val optionalBankAccount: Optional<BankAccount> = this.bankAccountRepository.findById(event.accountId!!)
        if (optionalBankAccount.isPresent) {
            val bankAccount: BankAccount = optionalBankAccount.get()
            bankAccount.balance = bankAccount.balance!!.add(event.creditAmount)
            this.bankAccountRepository.save(bankAccount)
        } else {
            throw AccountNotFoundException(event.accountId)
        }
    }

    @EventHandler
    @Throws(AccountNotFoundException::class)
    fun on(event: MoneyDebitedEvent) {
        log.debug("Handling an Account Debit command {}", event.accountId)
        val optionalBankAccount: Optional<BankAccount> = this.bankAccountRepository.findById(event.accountId!!)
        if (optionalBankAccount.isPresent) {
            val bankAccount: BankAccount = optionalBankAccount.get()
            bankAccount.balance = bankAccount.balance!!.subtract(event.debitAmount)
            this.bankAccountRepository.save(bankAccount)
        } else {
            throw AccountNotFoundException(event.accountId)
        }
    }
}