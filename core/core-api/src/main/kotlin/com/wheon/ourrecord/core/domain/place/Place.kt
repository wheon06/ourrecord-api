package com.wheon.ourrecord.core.domain.place

import java.math.BigDecimal

data class Place(
    val id: Long,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val longitude: BigDecimal,
    val latitude: BigDecimal,
)
