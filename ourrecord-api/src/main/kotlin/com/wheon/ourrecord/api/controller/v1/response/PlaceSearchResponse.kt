package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.client.naver.model.NaverClientPlaceResult
import java.math.BigDecimal

data class PlaceSearchResponse(
    val title: String,
    val address: String,
    val roadAddress: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
) {
    companion object {
        fun of(
            places: List<NaverClientPlaceResult.NaverClientPlaceItemResult>,
        ): List<PlaceSearchResponse> {
            return places.map {
                PlaceSearchResponse(
                    title = it.title,
                    address = it.address,
                    roadAddress = it.roadAddress,
                    latitude = it.latitude,
                    longitude = it.longitude,
                )
            }
        }
    }
}
