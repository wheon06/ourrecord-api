package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.Page
import com.wheon.ourrecord.storage.db.core.MetaPlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import org.springframework.stereotype.Component

@Component
class PlaceFinder(
    private val placeRepository: PlaceRepository,
    private val metaPlaceRepository: MetaPlaceRepository,
) {
    fun find(spaceId: Long, offsetLimit: OffsetLimit): Page<Place> {
        val result = metaPlaceRepository.findBySpaceIdAndStatusOrderByLastRecordedAtDesc(spaceId, EntityStatus.ACTIVE, offsetLimit.toPageable())
        val places = placeRepository.findByIdIn(result.content.map { it.placeId }).map {
            Place(
                id = it.id,
                name = it.name,
                address = it.address,
                roadAddress = it.roadAddress,
                thumbnailUrl = it.thumbnailUrl,
                longitude = it.longitude,
                latitude = it.latitude,
            )
        }
        return Page(places, result.hasNext())
    }
}
