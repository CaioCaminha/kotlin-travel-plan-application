package com.caminha.kotlintravelplanapi.domain.entities

import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import java.util.Date

data class TravelPlan(
    val destination: String,
    val startDate: Date,
    val endDate: Date,
    val preferences: Set<Pair<DestinationCategory, CategoryRating>>,
    val budget: Budget,
)
