package com.caminha.kotlintravelplanapi.application.gateway.r2dbc

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities.toEntity
import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.integration.TravelPlanRepository
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlanDetails
import com.caminha.kotlintravelplanapi.domain.ports.TravelPlanDetailsPort
import org.springframework.stereotype.Service

@Service
class TravelPlanDetailsDetailsR2DBCGateway(
    private val travelPlanRepository: TravelPlanRepository
): TravelPlanDetailsPort {
    override suspend fun findByTravelPlanId(travelPlanId: String): TravelPlanDetails? {
        return travelPlanRepository.findById(travelPlanId)
            ?.toDomain()
    }

    override suspend fun save(travelPlanDetails: TravelPlanDetails): TravelPlanDetails {
        return travelPlanRepository
            .save(travelPlanDetails.toEntity())
            .toDomain()
    }


}