package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.place.CouplePlaceMapMarker
import java.math.BigDecimal
import java.time.LocalDate

data class CouplePlaceMapMarkerResponse(
    val couplePlaceId: Long,
    val placeId: Long,
    val categoryCode: String?,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val recordCount: Long,
    val latestVisitedOn: LocalDate?,
    val thumbnailUrl: String,
) {
    companion object {
        fun of(markers: List<CouplePlaceMapMarker>): List<CouplePlaceMapMarkerResponse> {
            return markers.map {
                CouplePlaceMapMarkerResponse(
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
                    thumbnailUrl = it.thumbnailUrl,
                )
            }
        }
    }
}
