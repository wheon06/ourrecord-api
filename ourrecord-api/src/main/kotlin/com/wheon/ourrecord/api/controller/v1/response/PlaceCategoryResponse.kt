package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.place.PlaceCategory

data class PlaceCategoryResponse(
    val code: String,
    val displayName: String,
    val emoji: String,
) {
    companion object {
        fun of(
            categories: List<PlaceCategory>,
        ): List<PlaceCategoryResponse> {
            return categories.map {
                PlaceCategoryResponse(
                    code = it.code,
                    displayName = it.displayName,
                    emoji = it.emoji,
                )
            }
        }
    }
}
