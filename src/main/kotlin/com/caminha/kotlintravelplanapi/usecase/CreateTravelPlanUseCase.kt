package com.caminha.kotlintravelplanapi.usecase

import com.caminha.kotlintravelplanapi.domain.entities.DestinationPreferences
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.entities.toTravelPlanDetails
import com.caminha.kotlintravelplanapi.domain.ports.CreateTravelPlanExternalPort
import com.caminha.kotlintravelplanapi.domain.ports.DestinationPreferencesPort
import com.caminha.kotlintravelplanapi.domain.ports.TravelPlanDetailsPort
import kotlinx.coroutines.coroutineScope
import java.lang.UnsupportedOperationException

class CreateTravelPlanUseCase(
    private val createTravelPlanExternalPort: CreateTravelPlanExternalPort,
    private val travelPlanDetailsPort: TravelPlanDetailsPort,
    private val destinationPreferencesPort: DestinationPreferencesPort
) {

    suspend fun execute(
        travelPlan: TravelPlan
    ): TravelPlan = coroutineScope {
        val openAiResponse = createTravelPlanExternalPort
            .createTravelPlan(travelPlan)

        val travelPlanDetails = travelPlanDetailsPort.save(
            travelPlan.toTravelPlanDetails(
                externalTravelPlanning = openAiResponse.choices.first().message.content
            )
        )


        travelPlan.preferences.onEach {
            destinationPreferencesPort.save(
                DestinationPreferences(
                    travelPlanId = travelPlanDetails.id.toString(),
                    destinationCategory = it.first,
                    categoryRating = it.second
                )
            )
        }

        travelPlanDetails.toTravelPlan(
            travelPlan.preferences
        )
    }

}