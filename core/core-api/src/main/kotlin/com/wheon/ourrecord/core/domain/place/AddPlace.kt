package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.core.enums.PlaceSource
import java.math.BigDecimal

data class AddPlace(
    val source: PlaceSource,
    val externalPlaceId: String,
    val categoryCode: String?,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val providerCategory: String? = null,
    val rawPayload: Map<String, Any?> = emptyMap(),
)
