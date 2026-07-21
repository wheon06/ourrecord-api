package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import org.springframework.stereotype.Component

@Component
class PlaceReader(
    private val placeRepository: PlaceRepository,
) {
    fun getPlace(placeId: Long): Place {
        val entity = placeRepository.findByIdAndStatus(placeId, EntityStatus.ACTIVE)
            ?: throw CoreException(ErrorType.NOT_FOUND_DATA)

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
