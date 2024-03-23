package com.caminha.kotlintravelplanapi.domain.entities

import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import com.google.cloud.Timestamp
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.util.UUID

data class DestinationPreferences (
    val id: String = UUID.randomUUID().toString(),
    val travelPlanId: String,
    val destinationCategory: DestinationCategory,
    val categoryRating: CategoryRating,
)