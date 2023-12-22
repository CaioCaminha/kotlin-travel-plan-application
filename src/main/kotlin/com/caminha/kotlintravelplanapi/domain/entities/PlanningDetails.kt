package com.caminha.kotlintravelplanapi.domain.entities

import com.caminha.kotlintravelplanapi.domain.enum.TravelPlanStatus
import java.time.Instant
import java.util.Date
import java.util.UUID

data class PlanningDetails(
    val id: UUID,
    val destination: List<String>,
    val userId: UUID,
    val status: TravelPlanStatus,
    val startDate: Date,
    val endDate: Date,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = createdAt,
)