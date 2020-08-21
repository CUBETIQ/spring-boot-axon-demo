package com.cubetiqs.demo.axon.event

import java.math.BigDecimal

import java.util.UUID

data class AccountCreatedEvent(
    var id: UUID? = null,
    var initialBalance: BigDecimal? = null,
    var owner: String? = null
)