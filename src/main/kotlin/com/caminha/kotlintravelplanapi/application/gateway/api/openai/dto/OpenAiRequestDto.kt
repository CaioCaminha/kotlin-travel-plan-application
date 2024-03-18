package com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto

data class OpenAiRequestDto(
    val model: String = "gpt-3.5-turbo",
    val messages: List<MessageDto>
)

