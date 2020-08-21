package com.cubetiqs.demo.axon.repository

import com.cubetiqs.demo.axon.entity.BankAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BankAccountRepository : JpaRepository<BankAccount, UUID>