package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "place")
class PlaceEntity(
    val name: String,
    val address: String,
    val roadAddress: String?,
    @Column(nullable = false, precision = 9, scale = 6)
    val latitude: BigDecimal,
    @Column(nullable = false, precision = 9, scale = 6)
    val longitude: BigDecimal,
    val geoHash: String,
) : BaseEntity()
