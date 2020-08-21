package com.cubetiqs.demo.axon.dto

import java.math.BigDecimal

data class AccountCreationDTO(
    var initialBalance: BigDecimal? = null,
    var owner: String? = null
)