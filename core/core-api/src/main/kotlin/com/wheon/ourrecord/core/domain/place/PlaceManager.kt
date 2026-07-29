package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.PlaceEntity
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import org.springframework.stereotype.Component

@Component
class PlaceManager(
    private val placeRepository: PlaceRepository,
) {
    fun add(userId: Long, spaceId: Long, newPlace: NewPlace): Long {
        return placeRepository.findBySpaceIdAndExternalPlaceIdAndStatus(
            spaceId = spaceId,
            externalPlaceId = newPlace.externalPlaceId,
            status = EntityStatus.ACTIVE,
        )?.id ?: placeRepository.save(
            PlaceEntity(
                userId = userId,
                spaceId = spaceId,
                name = newPlace.name,
                address = newPlace.address,
                roadAddress = newPlace.roadAddress,
                longitude = newPlace.longitude,
                latitude = newPlace.latitude,
                externalPlaceId = newPlace.externalPlaceId,
            ),
        ).id
    }
}
