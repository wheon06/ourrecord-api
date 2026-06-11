package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.domain.record.CouplePlaceRecord
import com.wheon.ourrecord.domain.record.RecordReader
import com.wheon.ourrecord.support.ApiCoupleUser
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val couplePlaceManager: CouplePlaceManager,
    private val couplePlaceReader: CouplePlaceReader,
    private val placeCategoryReader: PlaceCategoryReader,
    private val recordReader: RecordReader,
) {
    fun addPlace(apiCoupleUser: ApiCoupleUser, place: AddPlace): Long {
        return couplePlaceManager.findOrCreate(
            coupleId = apiCoupleUser.coupleId,
            memberId = apiCoupleUser.memberId,
            place = place,
        )
    }

    fun updateCouplePlace(apiCoupleUser: ApiCoupleUser, couplePlaceId: Long, categoryCode: String?) {
        couplePlaceManager.update(
            coupleId = apiCoupleUser.coupleId,
            couplePlaceId = couplePlaceId,
            categoryCode = categoryCode,
        )
    }

    fun deleteCouplePlace(apiCoupleUser: ApiCoupleUser, couplePlaceId: Long) {
        couplePlaceManager.delete(
            coupleId = apiCoupleUser.coupleId,
            couplePlaceId = couplePlaceId,
        )
    }

    fun getCouplePlaceMapMarkers(apiCoupleUser: ApiCoupleUser): List<CouplePlaceMapMarker> {
        return couplePlaceReader.getMapMarkers(apiCoupleUser.coupleId)
    }

    fun getCouplePlaceRecords(apiCoupleUser: ApiCoupleUser, couplePlaceId: Long): List<CouplePlaceRecord> {
        couplePlaceReader.checkActiveCouplePlace(
            coupleId = apiCoupleUser.coupleId,
            couplePlaceId = couplePlaceId,
        )

        return recordReader.getCouplePlaceRecords(
            coupleId = apiCoupleUser.coupleId,
            couplePlaceId = couplePlaceId,
        )
    }

    fun getPlaceCategories(): List<PlaceCategory> {
        return placeCategoryReader.getPlaceCategories()
    }
}
