package com.caminha.kotlintravelplanapi.domain.ports

import com.caminha.kotlintravelplanapi.domain.entities.DestinationPreferences
import kotlinx.coroutines.flow.Flow

interface DestinationPreferencesPort {

    fun findAllByTravelPlanId(travelPlanId: String): Flow<DestinationPreferences>

}