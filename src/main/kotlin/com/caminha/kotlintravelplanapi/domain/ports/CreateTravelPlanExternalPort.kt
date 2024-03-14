package com.caminha.kotlintravelplanapi.domain.ports

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan

interface CreateTravelPlanExternalPort {


    suspend fun createTravelPlan(travelPlan: TravelPlan)

}