package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.client.kakao.model.KakaoClientPlaceResult
import com.wheon.ourrecord.core.enums.PlaceSource
import java.math.BigDecimal

data class PlaceSearchResponse(
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val source: PlaceSource,
    val externalPlaceId: String,
) {
    companion object {
        fun of(
            places: List<KakaoClientPlaceResult.KakaoClientPlaceItemResult>,
        ): List<PlaceSearchResponse> {
            return places.map {
                PlaceSearchResponse(
                    name = it.name,
                    address = it.address,
                    roadAddress = it.roadAddress,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    source = PlaceSource.KAKAO,
                    externalPlaceId = it.externalPlaceId,
                )
            }
        }
    }
}
