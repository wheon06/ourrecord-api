package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.MetaPlaceEntity
import com.wheon.ourrecord.storage.db.core.MetaPlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceEntity
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PlaceManager(
    private val placeRepository: PlaceRepository,
    private val metaPlaceRepository: MetaPlaceRepository,
) {
    @Transactional
    fun add(userId: Long, spaceId: Long, newPlace: NewPlace): Long {
        return placeRepository.findBySpaceIdAndExternalPlaceIdAndStatus(
            spaceId = spaceId,
            externalPlaceId = newPlace.externalPlaceId,
            status = EntityStatus.ACTIVE,
        )?.id ?: run {
            val savedPlace = placeRepository.save(
                PlaceEntity(
                    userId = userId,
                    spaceId = spaceId,
                    name = newPlace.name,
                    address = newPlace.address,
                    roadAddress = newPlace.roadAddress,
                    thumbnailUrl = "EMPTY",
                    longitude = newPlace.longitude,
                    latitude = newPlace.latitude,
                    externalPlaceId = newPlace.externalPlaceId,
                ),
            )
            metaPlaceRepository.save(
                MetaPlaceEntity(
                    spaceId = spaceId,
                    placeId = savedPlace.id,
                    recordCount = 0,
                    lastRecordedAt = savedPlace.createdAt,
                ),
            )

            return savedPlace.id
        }
    }
}
