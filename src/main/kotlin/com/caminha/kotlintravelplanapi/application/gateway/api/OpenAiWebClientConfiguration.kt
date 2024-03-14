package com.caminha.kotlintravelplanapi.application.gateway.api

import com.caminha.kotlintravelplanapi.application.configuration.api.WebClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class OpenAiWebClientConfiguration {

    @Value("\${openai-secret-api-key}")
    lateinit var openaiApi: String

    @Value("\${https_proxyHost}")
    lateinit var proxyHost: String

    @Value("\${https_proxyPort}")
    lateinit var proxyPort: String


    @Bean
    fun openAiWebClient(): WebClient {
        return WebClientBuilder.build(proxyHost, proxyPort, openaiApi)
    }

    @Bean
    fun openAiHttpClient(openAiWebClient: WebClient): OpenAiHttpClient {
        val httpServiceProxyFactory =
            HttpServiceProxyFactory.builder(WebClientAdapter.forClient(openAiWebClient))
                .build()
        return httpServiceProxyFactory.createClient(OpenAiHttpClient::class.java)
    }



}