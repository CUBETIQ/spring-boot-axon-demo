package com.cubetiqs.demo.axon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class AxonApplication

fun main(args: Array<String>) {
	runApplication<AxonApplication>(*args)
}

@RestController
@RequestMapping
class DefaultController {
	@GetMapping
	fun index(): ResponseEntity<Any> {
		return ResponseEntity.ok("ok")
	}
}