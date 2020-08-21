package com.cubetiqs.demo.axon.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.UUID

data class CreditMoneyCommand(
    @TargetAggregateIdentifier
    val accountId: UUID? = null,
    val creditAmount: BigDecimal? = null
)