package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.domain.place.NewPlace
import java.math.BigDecimal

data class AddPlaceRequest(
    val externalPlaceId: String,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
) {
    fun toNewPlace() = NewPlace(
        externalPlaceId = externalPlaceId,
        name = name,
        address = address,
        roadAddress = roadAddress,
        latitude = latitude,
        longitude = longitude,
    )
}
