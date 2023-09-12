package com.caminha.kotlintravelplanapi.domain.entities

import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.Companion
import com.caminha.kotlintravelplanapi.domain.enum.VisitationPlaces
import com.caminha.kotlintravelplanapi.domain.enum.VisitationRate

data class TravelPlanRequest(
    val location: String,
    val days: Int,
    val preferences: Set<Pair<VisitationPlaces, VisitationRate>>,
    val budget: Budget,
    val companion: Companion,
)

