package com.caminha.kotlintravelplanapi.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinTravelPlanApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinTravelPlanApiApplication>(*args)
}


//example of an extention function
fun KotlinTravelPlanApiApplication.getClassName(): String {return KotlinTravelPlanApiApplication::getClassName.name}