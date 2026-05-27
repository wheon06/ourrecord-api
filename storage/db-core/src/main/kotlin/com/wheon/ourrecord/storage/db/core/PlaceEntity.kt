package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.PlaceSource
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "place")
class PlaceEntity(
    val coupleId: Long,
    val createdByUserId: Long,
    val categoryCode: Long,
    val name: String,
    val address: String,
    val addressDetail: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val geoHash: String,
    @Enumerated(EnumType.STRING)
    val source: PlaceSource,
    val externalPlaceId: String?,
) : BaseEntity()
