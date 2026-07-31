package com.wheon.ourrecord.core.api.controller.v1.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.wheon.ourrecord.core.domain.place.MetaPlace
import com.wheon.ourrecord.core.domain.place.Place
import java.time.LocalDate

data class PlaceResponse(
    val id: Long,
    val name: String,
    val address: String,
    val thumbnailUrl: String,
    val recordCount: Int,
    @param:JsonProperty("lastRecordedAt")
    val lastVisitedAt: LocalDate,
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
                    lastVisitedAt = metaMap[it.id]!!.lastVisitedAt,
                )
            }.sortedByDescending { it.lastVisitedAt }
        }
    }
}
