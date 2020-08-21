package com.cubetiqs.demo.axon.api

import com.cubetiqs.demo.axon.dto.AccountCreationDTO
import com.cubetiqs.demo.axon.dto.MoneyAmountDTO
import com.cubetiqs.demo.axon.entity.BankAccount
import com.cubetiqs.demo.axon.service.AccountCommandService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping(path = ["/accounts"])
@Api(value = "Bank Account Commands", description = "Bank Account Commands API")
class AccountCommandController @Autowired constructor(
    private val accountCommandService: AccountCommandService
) {
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createAccount(@RequestBody creationDTO: AccountCreationDTO): CompletableFuture<BankAccount?> {
        return this.accountCommandService.createAccount(creationDTO)
    }

    @PutMapping(value = ["/credit/{accountId}"])
    fun creditMoneyToAccount(
        @PathVariable(value = "accountId") accountId: String,
        @RequestBody moneyCreditDTO: MoneyAmountDTO
    ): CompletableFuture<String?> {
        return this.accountCommandService.creditMoneyToAccount(accountId, moneyCreditDTO)
    }

    @PutMapping(value = ["/debit/{accountId}"])
    fun debitMoneyFromAccount(
        @PathVariable(value = "accountId") accountId: String,
        @RequestBody moneyDebitDTO: MoneyAmountDTO
    ): CompletableFuture<String?>? {
        return this.accountCommandService.debitMoneyFromAccount(accountId, moneyDebitDTO)
    }
}