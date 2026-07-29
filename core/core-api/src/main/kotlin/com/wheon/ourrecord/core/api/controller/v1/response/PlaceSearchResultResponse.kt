package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.client.kakao.model.KakaoClientPlaceResult

data class PlaceSearchResultResponse(
    val items: List<PlaceSearchResultItem>,
) {
    data class PlaceSearchResultItem(
        val externalPlaceId: String,
        val name: String,
        val address: String,
    )

    companion object {
        fun of(result: KakaoClientPlaceResult): PlaceSearchResultResponse {
            return PlaceSearchResultResponse(
                items = result.places.map {
                    PlaceSearchResultItem(
                        externalPlaceId = it.externalPlaceId,
                        name = it.name,
                        address = it.address,
                    )
                },
            )
        }
    }
}
