package com.cubetiqs.demo.axon.api

import com.cubetiqs.demo.axon.entity.BankAccount
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture

class AccountQueryController {
    @GetMapping("/{accountId}")
    fun findById(@PathVariable("accountId") accountId: String?): CompletableFuture<BankAccount?>? {
        return this.accountQueryService.findById(accountId)
    }

    @GetMapping("/{accountId}/events")
    fun listEventsForAccount(@PathVariable(value = "accountId") accountId: String?): List<Any?>? {
        return this.accountQueryService.listEventsForAccount(accountId)
    }
}