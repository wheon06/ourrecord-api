package com.wheon.ourrecord.domain.place

import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val placeCategoryReader: PlaceCategoryReader,
) {
    fun getPlaceCategories(): List<PlaceCategory> {
        return placeCategoryReader.getPlaceCategories()
    }
}
