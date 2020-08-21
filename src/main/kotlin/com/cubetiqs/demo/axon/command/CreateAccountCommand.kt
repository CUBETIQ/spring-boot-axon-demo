package com.cubetiqs.demo.axon.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.UUID

data class CreateAccountCommand(
    @TargetAggregateIdentifier
    val accountId: UUID? = null,
    val initialBalance: BigDecimal? = null,
    val owner: String? = null
)