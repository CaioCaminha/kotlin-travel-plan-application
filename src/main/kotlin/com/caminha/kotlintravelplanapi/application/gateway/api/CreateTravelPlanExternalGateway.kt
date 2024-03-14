package com.caminha.kotlintravelplanapi.application.gateway.api

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.ports.CreateTravelPlanExternalPort

class CreateTravelPlanExternalGateway(
    private val openAiHttpClient: OpenAiHttpClient
): CreateTravelPlanExternalPort {
    override suspend fun createTravelPlan(travelPlan: TravelPlan) {
        TODO("Not yet implemented")
    }
}