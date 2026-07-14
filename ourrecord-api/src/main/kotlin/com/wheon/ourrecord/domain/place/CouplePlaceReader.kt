package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.core.enums.CouplePlaceVisibility
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.domain.record.RecordMediaReader
import com.wheon.ourrecord.storage.db.core.CouplePlaceRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class CouplePlaceReader(
    private val couplePlaceRepository: CouplePlaceRepository,
    private val recordMediaReader: RecordMediaReader,
) {
    fun getMapMarkers(coupleId: Long): List<CouplePlaceMapMarker> {
        val markerRows = couplePlaceRepository.findMapMarkersByCoupleId(
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
            visibility = CouplePlaceVisibility.SAVED,
            recordState = RecordState.PUBLISHED,
        )
        val thumbnailByCouplePlaceId = recordMediaReader.getLatestRecordFirstThumbnails(
            coupleId = coupleId,
            couplePlaceIds = markerRows.map { it.couplePlaceId },
        ).associateBy { it.couplePlaceId }

        return markerRows.mapNotNull {
            val thumbnail = thumbnailByCouplePlaceId[it.couplePlaceId] ?: return@mapNotNull null

            CouplePlaceMapMarker(
                couplePlaceId = it.couplePlaceId,
                placeId = it.placeId,
                categoryCode = it.categoryCode,
                name = it.name,
                address = it.address,
                roadAddress = it.roadAddress,
                latitude = it.latitude,
                longitude = it.longitude,
                recordCount = it.recordCount,
                latestVisitedOn = it.latestVisitedOn,
                thumbnailUrl = thumbnail.thumbnailUrl,
            )
        }
    }

    fun checkActiveCouplePlace(coupleId: Long, couplePlaceId: Long) {
        couplePlaceRepository.findByIdAndCoupleIdAndStatus(
            id = couplePlaceId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
        ) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
    }
}
