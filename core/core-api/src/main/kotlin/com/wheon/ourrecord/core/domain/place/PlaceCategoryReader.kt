package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.storage.db.core.PlaceCategoryRepository
import org.springframework.stereotype.Component

@Component
class PlaceCategoryReader(
    private val placeCategoryRepository: PlaceCategoryRepository,
) {
    fun getPlaceCategories(): List<PlaceCategory> {
        val entities = placeCategoryRepository.findAll().sortedBy { it.sortOrder }
        return entities.map {
            PlaceCategory(
                code = it.code,
                displayName = it.displayName,
                emoji = it.emoji,
            )
        }
    }
}
