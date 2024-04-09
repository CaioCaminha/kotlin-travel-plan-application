package com.caminha.kotlintravelplanapi.application.gateway.api.openai

import com.caminha.kotlintravelplanapi.application.gateway.api.openai.common.OpenAiHttpClient
import com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto.MessageDto
import com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto.OpenAiRequestDto
import com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto.OpenAiResponseDto
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.caminha.kotlintravelplanapi.domain.ports.CreateTravelPlanExternalPort
import com.caminha.kotlintravelplanapi.utils.logInfo
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CreateTravelPlanExternalGateway(
    private val openAiHttpClient: OpenAiHttpClient,
    @Value("\${openai-secret-api-key}")
    private val openAiToken: String,
): CreateTravelPlanExternalPort {
    override suspend fun createTravelPlan(travelPlan: TravelPlan): OpenAiResponseDto {
        logInfo(openAiToken)
        return openAiHttpClient.chatCompletion(
            openAiToken,
            OpenAiRequestDto(
                messages = listOf(
                    generatePrompt(travelPlan)
                )
            )
        ).awaitSingle()
    }
}


private fun generatePrompt(travelPlan: TravelPlan): MessageDto {

    val categoriesList = travelPlan.preferences.map {
        if(it.second == CategoryRating.VERY_LIKELY || it.second == CategoryRating.LIKELY){
            it.first
        }
    }

    return MessageDto(
        content = "Generate a concise travel plan to ${travelPlan.destination} " +
                "from ${travelPlan.startDate} to ${travelPlan.endDate}, " +
                "include places from this categories $categoriesList " +
                "and the budget being ${travelPlan.budget}"
    )
}