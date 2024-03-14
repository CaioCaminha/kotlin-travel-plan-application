package com.caminha.kotlintravelplanapi.domain.entities

import com.caminha.kotlintravelplanapi.application.controller.dto.TravelPlanRequestDto
import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategories
import com.caminha.kotlintravelplanapi.domain.enum.DestinationRate
import java.time.Instant

data class TravelPlan(
    val destination: String,
    val startDate: Instant,
    val endDate: Instant,
    val preferences: Set<Pair<DestinationCategories, DestinationRate>>,
    val budget: Budget,
)


//extension function
fun TravelPlanRequestDto.toDomain() = TravelPlan(
    destination = destination,
    startDate = startDate,
    endDate = endDate,
    preferences = preferences,
    budget = budget,
)
