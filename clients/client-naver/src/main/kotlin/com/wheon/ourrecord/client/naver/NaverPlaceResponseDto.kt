package com.wheon.ourrecord.client.naver

import com.wheon.ourrecord.client.naver.model.NaverClientPlaceResult
import java.math.BigDecimal

private const val NAVER_COORDINATE_SCALE = 7

internal data class NaverPlaceResponseDto(
    val items: List<NaverPlaceItemResponseDto>,
) {
    fun toResult(): NaverClientPlaceResult {
        return NaverClientPlaceResult(
            items = items.map {
                NaverClientPlaceResult.NaverClientPlaceItemResult(
                    title = it.title,
                    category = it.category,
                    address = it.address,
                    roadAddress = it.roadAddress,
                    longitude = it.mapx.toCoordinate(),
                    latitude = it.mapy.toCoordinate(),
                )
            },
        )
    }

    data class NaverPlaceItemResponseDto(
        val title: String,
        val link: String,
        val category: String,
        val description: String,
        val address: String,
        val roadAddress: String,
        val mapx: BigDecimal,
        val mapy: BigDecimal,
    )
}

private fun BigDecimal.toCoordinate(): BigDecimal {
    return movePointLeft(NAVER_COORDINATE_SCALE)
}
