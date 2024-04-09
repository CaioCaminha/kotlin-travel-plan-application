package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.integration

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities.TravelPlanDetailsEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TravelPlanRepository :
    CoroutineCrudRepository<TravelPlanDetailsEntity, String> {

    }