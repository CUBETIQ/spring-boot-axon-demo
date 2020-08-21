package com.cubetiqs.demo.axon.service

import com.cubetiqs.demo.axon.dto.AccountCreationDTO
import com.cubetiqs.demo.axon.dto.MoneyAmountDTO
import com.cubetiqs.demo.axon.entity.BankAccount
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
interface AccountCommandService {
    fun createAccount(creationDTO: AccountCreationDTO): CompletableFuture<BankAccount?>

    fun creditMoneyToAccount(
        accountId: String?,
        moneyCreditDTO: MoneyAmountDTO
    ): CompletableFuture<String?>

    fun debitMoneyFromAccount(
        accountId: String?,
        moneyDebitDTO: MoneyAmountDTO
    ): CompletableFuture<String?>
}