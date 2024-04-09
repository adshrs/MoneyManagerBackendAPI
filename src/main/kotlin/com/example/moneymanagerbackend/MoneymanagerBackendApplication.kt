package com.example.moneymanagerbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class MoneymanagerBackendApplication {
	@GetMapping("/hello")
	fun hello(): String {
		return "Hello Aditya!"
	}
}

fun main(args: Array<String>) {
	runApplication<MoneymanagerBackendApplication>(*args)
}
