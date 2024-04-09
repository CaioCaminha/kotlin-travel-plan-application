package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common.PersistableEntity
import com.caminha.kotlintravelplanapi.domain.entities.TravelPlanDetails
import com.caminha.kotlintravelplanapi.domain.enum.Budget
import com.google.cloud.Date
import com.google.cloud.Timestamp
import jakarta.annotation.Nullable
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("travel_plan_details_entity")
data class TravelPlanDetailsEntity
@PersistenceCreator
constructor(
    @Id
    @Column("id")
    private val id: String = UUID.randomUUID().toString(),
    @Column("destination")
    val destination: String,
    @Column("start_date")
    val startDate: Date,
    @Column("end_date")
    val endDate: Date,
    @Column("budget")
    val budget: String,
    @Column("external_travel_planning")
    @Nullable
    val externalTravelPlanning: String?,
    @Column("created_at")
    override val createdAt: Timestamp = Timestamp.now(),
    @Column("updated_at")
    override var updatedAt: Timestamp = createdAt
) : PersistableEntity<String> {
    override fun getId(): String {
        return id
    }

    fun toDomain() = TravelPlanDetails(
        destination = destination,
        startDate = Date.toJavaUtilDate(startDate),
        endDate = Date.toJavaUtilDate(endDate),
        budget = Budget.valueOf(budget),
        externalTravelPlanning = externalTravelPlanning
    )
}

fun TravelPlanDetails.toEntity() = TravelPlanDetailsEntity (
    destination = destination,
    startDate = Date.fromJavaUtilDate(startDate),
    endDate = Date.fromJavaUtilDate(endDate),
    budget = budget.toString(),
    externalTravelPlanning = externalTravelPlanning,
    createdAt = Timestamp.now(),
    updatedAt = Timestamp.now(),
)