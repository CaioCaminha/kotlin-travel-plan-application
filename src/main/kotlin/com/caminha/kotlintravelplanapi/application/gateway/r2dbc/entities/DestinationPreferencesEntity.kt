package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common.PersistableEntity
import com.caminha.kotlintravelplanapi.domain.entities.DestinationPreferences
import com.caminha.kotlintravelplanapi.domain.enum.DestinationCategory
import com.caminha.kotlintravelplanapi.domain.enum.CategoryRating
import com.google.cloud.Timestamp
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("destination_preferences")
data class DestinationPreferencesEntity
@PersistenceCreator
constructor(
    @Id
    @Column("id")
    private val id: String = UUID.randomUUID().toString(),
    @Column("travel_plan_id")
    val travelPlanId: String,
    @Column("destination_category")
    val destinationCategory: DestinationCategory,
    @Column("category_rating")
    val categoryRating: CategoryRating,
    @Column("created_at")
    override val createdAt: Timestamp = Timestamp.now(),
    @Column("updated_at")
    override var updatedAt: Timestamp = createdAt,
): PersistableEntity<String> {
    override fun getId(): String {
        return id
    }

    fun toDomain() = DestinationPreferences(
        id = UUID.fromString(id),
        travelPlanId = travelPlanId,
        destinationCategory = destinationCategory,
        categoryRating = categoryRating,
    )

}

fun DestinationPreferences.toEntity() = DestinationPreferencesEntity(
    id = id.toString(),
    travelPlanId = travelPlanId,
    destinationCategory = destinationCategory,
    categoryRating = categoryRating,
    createdAt = Timestamp.now(),
    updatedAt = Timestamp.now(),
)