package com.wheon.ourrecord.client.kakao.model

import java.math.BigDecimal

data class KakaoClientPlaceResult(
    val places: List<KakaoClientPlaceItemResult>,
) {
    data class KakaoClientPlaceItemResult(
        val externalPlaceId: String,
        val name: String,
        val address: String,
        val roadAddress: String?,
        val longitude: BigDecimal,
        val latitude: BigDecimal,
    )
}
