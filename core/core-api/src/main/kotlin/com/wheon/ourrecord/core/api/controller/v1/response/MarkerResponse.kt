package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.domain.place.MetaPlace
import com.wheon.ourrecord.core.domain.place.Place
import java.math.BigDecimal

data class MarkerResponse(
    val placeId: Long,
    val thumbnailUrl: String,
    val name: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val recordCount: Int,
) {
    companion object {
        fun of(places: List<Place>, metaMap: Map<Long, MetaPlace>): List<MarkerResponse> {
            return places.map {
                MarkerResponse(
                    placeId = it.id,
                    thumbnailUrl = it.thumbnailUrl,
                    name = it.name,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    recordCount = metaMap[it.id]!!.recordCount,
                )
            }
        }
    }
}
