package com.wheon.ourrecord.client.kakao

import com.fasterxml.jackson.annotation.JsonProperty
import com.wheon.ourrecord.client.kakao.model.KakaoClientPlaceResult
import java.math.BigDecimal

internal data class KakaoPlaceResponseDto(
    val documents: List<KakaoPlaceDocumentResponseDto>,
) {
    fun toResult(): KakaoClientPlaceResult {
        return KakaoClientPlaceResult(
            documents.map {
                KakaoClientPlaceResult.KakaoClientPlaceItemResult(
                    externalPlaceId = it.id,
                    name = it.placeName,
                    address = it.addressName,
                    roadAddress = it.roadAddressName.ifBlank { null },
                    longitude = it.x,
                    latitude = it.y,
                )
            },
        )
    }

    internal data class KakaoPlaceDocumentResponseDto(
        val id: String,
        @JsonProperty("place_name")
        val placeName: String,
        @JsonProperty("address_name")
        val addressName: String,
        @JsonProperty("road_address_name")
        val roadAddressName: String,
        val x: BigDecimal, // longitude
        val y: BigDecimal, // latitude
    )
}
