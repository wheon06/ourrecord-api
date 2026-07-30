package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.domain.place.MetaPlace
import com.wheon.ourrecord.core.domain.place.Place
import java.time.LocalDateTime

data class PlaceResponse(
    val id: Long,
    val name: String,
    val address: String,
    val thumbnailUrl: String,
    val recordCount: Int,
    val lastRecordedAt: LocalDateTime,
) {
    companion object {
        fun of(places: List<Place>, metaMap: Map<Long, MetaPlace>): List<PlaceResponse> {
            return places.map {
                PlaceResponse(
                    id = it.id,
                    name = it.name,
                    address = it.address,
                    thumbnailUrl = it.thumbnailUrl,
                    recordCount = metaMap[it.id]!!.recordCount,
                    lastRecordedAt = metaMap[it.id]!!.lastRecordedAt,
                )
            }.sortedByDescending { it.lastRecordedAt }
        }
    }
}
