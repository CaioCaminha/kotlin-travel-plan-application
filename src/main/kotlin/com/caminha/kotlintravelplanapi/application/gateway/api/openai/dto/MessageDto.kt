package com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto

data class MessageDto(
    val role: String = "user",
    val content: String,
)