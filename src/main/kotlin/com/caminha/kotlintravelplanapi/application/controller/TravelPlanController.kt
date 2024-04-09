package com.caminha.kotlintravelplanapi.application.controller

import com.caminha.kotlintravelplanapi.application.controller.dto.TravelPlanRequestDto
import com.caminha.kotlintravelplanapi.application.controller.dto.TravelPlanResponseDto
import com.caminha.kotlintravelplanapi.application.controller.dto.toResponseDto
import com.caminha.kotlintravelplanapi.usecase.CreateTravelPlanUseCase
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/travelplan")
class TravelPlanController(
    private val createTravelPlanUseCase: CreateTravelPlanUseCase,
) {
    companion object {
        private val mapper = jacksonObjectMapper().registerModule(
            KotlinModule.Builder()
                .build()
        ).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }


    @PostMapping
    suspend fun createPlan(
        @RequestBody requestModel: String,
    ): TravelPlanResponseDto = coroutineScope {

        val request = mapper.readValue(requestModel, TravelPlanRequestDto::class.java)

        createTravelPlanUseCase
            .execute(request.toDomain())
            .toResponseDto()
    }

}