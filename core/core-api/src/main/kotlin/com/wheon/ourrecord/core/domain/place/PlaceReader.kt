package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class PlaceReader(
    private val placeRepository: PlaceRepository,
) {
    fun getPlace(placeId: Long): Place {
        val entity = placeRepository.findByIdAndStatus(placeId, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        return Place(
            id = entity.id,
            name = entity.name,
            address = entity.address,
            roadAddress = entity.roadAddress,
            longitude = entity.longitude,
            latitude = entity.latitude,
        )
    }
}
