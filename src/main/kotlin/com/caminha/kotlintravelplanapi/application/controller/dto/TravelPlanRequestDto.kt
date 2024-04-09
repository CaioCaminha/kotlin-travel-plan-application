package com.caminha.kotlintravelplanapi.application.controller.dto

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.cloud.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class TravelPlanRequestDto(
    val destination: String,
    val startDate: String,
    val endDate: String,
    val preferences: List<Pair<DestinationCategory, CategoryRating>>,
    val budget: Budget,
){


    fun toDomain() = TravelPlan(
        destination = destination,
        startDate = Date.toJavaUtilDate(Date.parseDate(startDate)),
        endDate = Date.toJavaUtilDate(Date.parseDate(endDate)),
        preferences = preferences,
        budget = budget,
    )

}