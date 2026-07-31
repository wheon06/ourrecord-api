package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.storage.db.core.MetaPlaceRepository
import org.springframework.stereotype.Component

@Component
class PlaceReader(
    private val metaPlaceRepository: MetaPlaceRepository,
) {
    fun readMetaMap(places: List<Place>): Map<Long, MetaPlace> {
        val metas = metaPlaceRepository.findByPlaceIdIn(places.map { it.id }).map {
            MetaPlace(
                placeId = it.placeId,
                recordCount = it.recordCount,
                lastVisitedAt = it.lastVisitedAt,
            )
        }
        return metas.associateBy { it.placeId }
    }
}
