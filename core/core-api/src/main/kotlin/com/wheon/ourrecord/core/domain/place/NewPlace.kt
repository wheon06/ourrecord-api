package com.wheon.ourrecord.core.domain.place

import java.math.BigDecimal

data class NewPlace(
    val externalPlaceId: String,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
)
