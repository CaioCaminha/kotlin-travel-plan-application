package com.caminha.kotlintravelplanapi.application.gateway.api

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import reactor.core.publisher.Mono

@HttpExchange(url = "")
interface OpenAiHttpClient {

    @PostExchange("/chat/completions")
    fun chatCompletion(
        @RequestHeader("Authorization") token: String,
        @RequestBody body: String,
    ): Mono<OpenAiResponseDto>
}