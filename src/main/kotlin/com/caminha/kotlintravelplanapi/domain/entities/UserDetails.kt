package com.caminha.kotlintravelplanapi.domain.entities

import java.time.Instant
import java.util.UUID

data class UserDetails(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = createdAt,
)