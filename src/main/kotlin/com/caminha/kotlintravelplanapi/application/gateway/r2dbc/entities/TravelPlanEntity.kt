package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common.PersistableEntity
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlan
import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import com.google.cloud.Date
import com.google.cloud.Timestamp
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import java.util.UUID

data class TravelPlanEntity
@PersistenceCreator
constructor(
    @Column("id")
    val id: String = UUID.randomUUID().toString(),
    @Column("destination")
    val destination: String,
    @Column("start_date")
    val startDate: Date,
    @Column("end_date")
    val endDate: Date,
    @Column("budget")
    val budget: String,
    @Column("created_at")
    override val createdAt: Timestamp,
    @Column("updated_at")
    override var updatedAt: Timestamp
) : PersistableEntity<String> {
    override fun getId(): String {
        return id
    }

    fun toDomain(
        preferences: Set<Pair<DestinationCategory, CategoryRating>>
    ) = TravelPlan(
        destination = destination,
        startDate = Date.toJavaUtilDate(startDate),
        endDate = Date.toJavaUtilDate(endDate),
        budget = Budget.valueOf(budget),
        preferences = preferences
    )
}

fun TravelPlan.toEntity() = TravelPlanEntity (
    destination = destination,
    startDate = Date.fromJavaUtilDate(startDate),
    endDate = Date.fromJavaUtilDate(endDate),
    budget = budget.toString(),
    createdAt = Timestamp.now(),
    updatedAt = Timestamp.now(),
)