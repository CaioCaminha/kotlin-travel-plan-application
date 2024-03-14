package com.caminha.kotlintravelplanapi.usecase

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.ports.CreateTravelPlanExternalPort

class CreateTravelPlanUseCase(
    private val createTravelPlanExternalPort: CreateTravelPlanExternalPort,
    //private val travelPlanPort: TravelPlanPort,
) {

    suspend fun execute(
        travelPlan: TravelPlan
    ){

    }

}