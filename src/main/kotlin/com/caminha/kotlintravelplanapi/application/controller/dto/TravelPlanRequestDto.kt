package com.caminha.kotlintravelplanapi.application.controller.dto

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class TravelPlanRequestDto(
    val destination: String,
    val startDate: Date,
    val endDate: Date,
    val preferences: Set<Pair<DestinationCategory, CategoryRating>>,
    val budget: Budget,
){


    fun toDomain() = TravelPlan(
        destination = destination,
        startDate = startDate,
        endDate = endDate,
        preferences = preferences,
        budget = budget,
    )

}