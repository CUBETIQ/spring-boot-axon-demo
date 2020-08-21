package com.cubetiqs.demo.axon.entity

import java.io.Serializable
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "bank_accounts")
data class BankAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var owner: String? = null,

    var balance: BigDecimal? = null
) : Serializable