package com.caminha.kotlintravelplanapi.application.gateway.r2dbc

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities.toEntity
import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.integration.DestinationPreferencesRepository
import com.caminha.kotlintravelplanapi.domain.entities.DestinationPreferences
import com.caminha.kotlintravelplanapi.domain.ports.DestinationPreferencesPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service

@Service
class DestinationPreferencesR2DBCGateway(
    private val destinationPreferencesRepository: DestinationPreferencesRepository
): DestinationPreferencesPort {
    override fun findAllByTravelPlanId(travelPlanId: String): Flow<DestinationPreferences> {
        return destinationPreferencesRepository
            .findAllByTravelPlanId(travelPlanId)
            .map { it.toDomain() }
    }

    override suspend fun save(destinationPreferences: DestinationPreferences): DestinationPreferences? {
        return destinationPreferencesRepository
            .save(destinationPreferences.toEntity())
            .toDomain()
    }
}