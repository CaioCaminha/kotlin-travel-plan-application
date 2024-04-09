package com.caminha.kotlintravelplanapi.domain.entities

import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import java.util.UUID

data class DestinationPreferences (
    val id: UUID = UUID.randomUUID(),
    val travelPlanId: String,
    val destinationCategory: DestinationCategory,
    val categoryRating: CategoryRating,
)



