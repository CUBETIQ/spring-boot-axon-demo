package com.cubetiqs.demo.axon.event

import java.math.BigDecimal
import java.util.UUID

data class MoneyDebitedEvent(
    val accountId: UUID? = null,
    val debitAmount: BigDecimal? = null
)