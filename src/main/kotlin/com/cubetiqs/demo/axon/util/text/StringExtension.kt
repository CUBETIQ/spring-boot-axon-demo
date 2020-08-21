package com.cubetiqs.demo.axon.util.text

import java.util.UUID

fun String?.formatUuid(): UUID {
    return UUID.fromString(this)
}