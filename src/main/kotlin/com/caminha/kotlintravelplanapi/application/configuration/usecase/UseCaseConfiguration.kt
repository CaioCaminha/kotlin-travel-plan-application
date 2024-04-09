package com.caminha.kotlintravelplanapi.application.configuration.usecase

import com.caminha.kotlintravelplanapi.domain.ports.CreateTravelPlanExternalPort
import com.caminha.kotlintravelplanapi.domain.ports.DestinationPreferencesPort
import com.caminha.kotlintravelplanapi.domain.ports.TravelPlanDetailsPort
import com.caminha.kotlintravelplanapi.usecase.CreateTravelPlanUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun createTravelPlanUseCase(
        createTravelPlanExternalPort: CreateTravelPlanExternalPort,
        travelPlanDetailsPort: TravelPlanDetailsPort,
        destinationPreferencesPort: DestinationPreferencesPort,
    ): CreateTravelPlanUseCase = CreateTravelPlanUseCase(
        createTravelPlanExternalPort = createTravelPlanExternalPort,
        travelPlanDetailsPort = travelPlanDetailsPort,
        destinationPreferencesPort = destinationPreferencesPort,
    )

}