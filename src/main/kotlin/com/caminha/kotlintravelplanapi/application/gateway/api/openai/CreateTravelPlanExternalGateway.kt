package com.caminha.kotlintravelplanapi.application.gateway.api.openai

import com.caminha.kotlintravelplanapi.application.gateway.api.openai.common.OpenAiHttpClient
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.ports.CreateTravelPlanExternalPort
import org.springframework.beans.factory.annotation.Value

class CreateTravelPlanExternalGateway(
    private val openAiHttpClient: OpenAiHttpClient,
    @Value("\${openai-secret-api-key}")
    private val openAiToken: String,
): CreateTravelPlanExternalPort {
    override suspend fun createTravelPlan(travelPlan: TravelPlan) {
        TODO("Not yet implemented")
    }
}