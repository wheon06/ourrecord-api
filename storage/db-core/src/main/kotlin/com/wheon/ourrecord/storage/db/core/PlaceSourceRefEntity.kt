package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.PlaceSource
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(
    name = "place_source_ref",
    indexes = [
        Index(name = "udx_place_source_ref_source_external_place_id", columnList = "source, externalPlaceId", unique = true),
    ],
)
class PlaceSourceRefEntity(
    val placeId: Long,
    @Enumerated(EnumType.STRING)
    val source: PlaceSource,
    val externalPlaceId: String,
    val providerName: String?,
    val providerCategory: String?,
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    val rawPayload: Map<String, Any?>,
) : BaseNoStatusEntity()
