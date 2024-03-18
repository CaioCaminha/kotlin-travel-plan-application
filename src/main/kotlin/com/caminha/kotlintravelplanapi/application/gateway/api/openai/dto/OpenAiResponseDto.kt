package com.caminha.kotlintravelplanapi.application.gateway.api.openai.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OpenAiResponseDto(
    val id: String,
    val model: String,
    val system: String,
    val choices: List<ChoiceDto>,
    val usage: Usage
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ChoiceDto(
    val index: Number,
    val message: MessageDto,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Usage(
    @JsonProperty("prompt_tokens")
    val promptTokens: Number,
    @JsonProperty("completion_tokens")
    val completionTokens: Number,
    @JsonProperty("total_tokens")
    val totalTokens: Number
)
