package com.cubetiqs.demo.axon.service

import com.cubetiqs.demo.axon.command.CreateAccountCommand
import com.cubetiqs.demo.axon.command.CreditMoneyCommand
import com.cubetiqs.demo.axon.command.DebitMoneyCommand
import com.cubetiqs.demo.axon.dto.AccountCreationDTO
import com.cubetiqs.demo.axon.dto.MoneyAmountDTO
import com.cubetiqs.demo.axon.entity.BankAccount
import com.cubetiqs.demo.axon.util.text.formatUuid
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Service
class AccountCommandServiceImpl @Autowired constructor(
    private val commandGateway: CommandGateway
) : AccountCommandService {
    override fun createAccount(creationDTO: AccountCreationDTO): CompletableFuture<BankAccount?> {
        return commandGateway.send(
            CreateAccountCommand(
                UUID.randomUUID(),
                creationDTO.initialBalance,
                creationDTO.owner,
            )
        )
    }

    override fun creditMoneyToAccount(accountId: String?, moneyCreditDTO: MoneyAmountDTO): CompletableFuture<String?> {
        return commandGateway.send(
            CreditMoneyCommand(
                accountId.formatUuid(),
                moneyCreditDTO.amount
            )
        )
    }

    override fun debitMoneyFromAccount(accountId: String?, moneyDebitDTO: MoneyAmountDTO): CompletableFuture<String?> {
        return commandGateway.send(
            DebitMoneyCommand(
                accountId.formatUuid(),
                moneyDebitDTO.amount
            )
        )
    }


}