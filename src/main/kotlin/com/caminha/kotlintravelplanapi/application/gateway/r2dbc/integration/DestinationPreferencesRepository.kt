package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.integration

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities.DestinationPreferencesEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DestinationPreferencesRepository :
    CoroutineCrudRepository<DestinationPreferencesEntity, String> {

        fun findAllByTravelPlanId(travelPlanId: String): Flow<DestinationPreferencesEntity>

}