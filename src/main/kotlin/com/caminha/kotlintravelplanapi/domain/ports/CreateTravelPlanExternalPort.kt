package com.caminha.kotlintravelplanapi.domain.ports

import com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto.OpenAiResponseDto
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan

interface CreateTravelPlanExternalPort {
    suspend fun createTravelPlan(travelPlan: TravelPlan): OpenAiResponseDto

}