package com.wheon.ourrecord.client.naver.model

import java.math.BigDecimal

data class NaverClientPlaceResult(
    val items: List<NaverClientPlaceItemResult>,
) {
    data class NaverClientPlaceItemResult(
        val title: String,
        val category: String,
        val address: String,
        val roadAddress: String,
        val latitude: BigDecimal,
        val longitude: BigDecimal,
    )
}
