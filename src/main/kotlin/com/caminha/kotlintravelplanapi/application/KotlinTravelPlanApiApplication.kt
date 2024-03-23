package com.caminha.kotlintravelplanapi.application

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@AutoConfiguration
class KotlinTravelPlanApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinTravelPlanApiApplication>(*args)
}



