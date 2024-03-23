package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.entities

import com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common.PersistableEntity
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
    val id: String = UUID.randomUUID().toString(),
    @Column("travel_plan_id")
    val travelPlanId: String,
    @Column("destination_category")
    val destinationCategory: DestinationCategory,
    @Column("category_rating")
    val categoryRating: CategoryRating,
    @Column("created_at")
    override val createdAt: Timestamp,
    @Column("updated_at")
    override var updatedAt: Timestamp,
): PersistableEntity<String> {
    override fun getId(): String {
        return id
    }




}