package com.cubetiqs.demo.axon.service

import com.cubetiqs.demo.axon.entity.BankAccount
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
interface AccountQueryService {
    fun findById(accountId: String?): CompletableFuture<BankAccount?>
    fun listEventsForAccount(accountId: String?): List<Any?>
}