package com.caminha.kotlintravelplanapi.application.controller.dto

import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategories
import com.caminha.kotlintravelplanapi.domain.enum.DestinationRate
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class TravelPlanRequestDto(
    val destination: String,
    val startDate: Instant,
    val endDate: Instant,
    val preferences: Set<Pair<DestinationCategories, DestinationRate>>,
    val budget: Budget,
)