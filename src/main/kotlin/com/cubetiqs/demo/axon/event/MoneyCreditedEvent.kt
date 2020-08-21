package com.cubetiqs.demo.axon.event

import java.math.BigDecimal
import java.util.UUID

data class MoneyCreditedEvent(
    val accountId: UUID? = null,
    val creditAmount: BigDecimal? = null
)