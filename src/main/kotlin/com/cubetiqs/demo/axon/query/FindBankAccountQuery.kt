package com.cubetiqs.demo.axon.query

import java.util.UUID

data class FindBankAccountQuery(
    val accountId: UUID
)