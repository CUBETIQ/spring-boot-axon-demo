package com.cubetiqs.demo.axon.exception

import java.math.BigDecimal

import java.util.UUID

class InsufficientBalanceException(accountId: UUID, debitAmount: BigDecimal) : Throwable(
    "Insufficient Balance: Cannot debit " + debitAmount +
        " from account number [" + accountId.toString() + "]"
)