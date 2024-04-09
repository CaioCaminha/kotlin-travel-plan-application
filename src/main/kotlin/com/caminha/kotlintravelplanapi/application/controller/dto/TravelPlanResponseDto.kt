package com.caminha.kotlintravelplanapi.application.controller.dto

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan

data class TravelPlanResponseDto(
    val travelPlanning: String,
    val configuredTravelPlan: TravelPlan,
)

fun TravelPlan.toResponseDto() = TravelPlanResponseDto(
    travelPlanning = externalTravelPlanning ?: throw ClassNotFoundException("externalTravelPlanning has not been initialized"),
    configuredTravelPlan = this
)
