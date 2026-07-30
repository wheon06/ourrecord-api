package com.wheon.ourrecord.core.domain.record

import java.math.BigDecimal

data class RecordPlace(
    val couplePlaceId: Long,
    val placeId: Long,
    val categoryCode: String?,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
)
