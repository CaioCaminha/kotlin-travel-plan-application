package com.caminha.kotlintravelplanapi.domain.ports

import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlanDetails

interface TravelPlanDetailsPort {

    suspend fun findByTravelPlanId(travelPlanId: String): TravelPlanDetails?

    suspend fun save(travelPlanDetails: TravelPlanDetails): TravelPlanDetails

}