package com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan

data class OpenAiRequestDto(
    val model: String = "gpt-3.5-turbo",
    val messages: List<MessageDto>
)


