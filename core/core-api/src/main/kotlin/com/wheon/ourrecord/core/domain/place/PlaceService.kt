package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val placeManager: PlaceManager,
) {
    fun addPlace(user: User, spaceId: Long, newPlace: NewPlace): Long {
        return placeManager.add(user.id, spaceId, newPlace)
    }
}
