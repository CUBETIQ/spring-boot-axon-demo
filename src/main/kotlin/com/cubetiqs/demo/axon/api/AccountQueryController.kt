package com.cubetiqs.demo.axon.api

import com.cubetiqs.demo.axon.entity.BankAccount
import com.cubetiqs.demo.axon.service.AccountQueryService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping(path = ["/accounts"])
@Api(value = "Bank Account Queries", description = "Bank Account Query Events API")
class AccountQueryController @Autowired constructor(
    private val accountQueryService: AccountQueryService
) {
    @GetMapping("/{accountId}")
    fun findById(@PathVariable("accountId") accountId: String?): CompletableFuture<BankAccount?> {
        return this.accountQueryService.findById(accountId)
    }

    @GetMapping("/{accountId}/events")
    fun listEventsForAccount(@PathVariable(value = "accountId") accountId: String?): List<Any?> {
        return this.accountQueryService.listEventsForAccount(accountId)
    }
}