package com.caminha.kotlintravelplanapi.domain.entities

import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import java.time.Instant
import java.util.Date
import java.util.UUID

data class TravelPlanDetails (
    val id: UUID = UUID.randomUUID(),
    val destination: String,
    val startDate: Date,
    val endDate: Date,
    val budget: Budget,
    val externalTravelPlanning: String? = null,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = createdAt,
) {

    fun toTravelPlan(
        preferences: List<Pair<DestinationCategory, CategoryRating>>
    ) = TravelPlan(
        destination = destination,
        startDate = startDate,
        endDate = endDate,
        budget = budget,
        externalTravelPlanning = externalTravelPlanning,
        preferences = preferences
    )

}

fun TravelPlan.toTravelPlanDetails(
    externalTravelPlanning: String
) = TravelPlanDetails(
    destination = destination,
    startDate = startDate,
    endDate = endDate,
    budget = budget,
    externalTravelPlanning = externalTravelPlanning,
)