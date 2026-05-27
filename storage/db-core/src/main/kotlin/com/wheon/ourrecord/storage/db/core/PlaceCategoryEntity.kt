package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "place_category",
    indexes = [
        Index(name = "udx_place_category_code", columnList = "code", unique = true),
    ],
)
class PlaceCategoryEntity(
    val code: String,
    val displayName: String,
    val emoji: String,
    val sortOrder: Int,
) : BaseIdEntity()
