package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.Page
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val placeManager: PlaceManager,
    private val placeFinder: PlaceFinder,
    private val placeReader: PlaceReader,
) {
    fun addPlace(user: User, spaceId: Long, newPlace: NewPlace): Long {
        return placeManager.add(user.id, spaceId, newPlace)
    }

    fun getPlaces(spaceId: Long, offsetLimit: OffsetLimit): Page<Place> {
        return placeFinder.find(spaceId, offsetLimit)
    }

    fun readMetaMap(spaceId: Long, places: List<Place>): Map<Long, MetaPlace> {
        return placeReader.readMetaMap(spaceId, places)
    }
}
