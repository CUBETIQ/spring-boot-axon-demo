package com.cubetiqs.demo.axon.exception

import java.util.UUID

class AccountNotFoundException(id: UUID?) : Throwable("Cannot found account number [$id]")