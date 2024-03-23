package com.caminha.kotlintravelplanapi.domain.enum

enum class CategoryRating(
    val value: Int
) {
    UNKNOWN(1), VERY_UNLIKELY(2), UNLIKELY(3), LIKELY(4), VERY_LIKELY(5)
}