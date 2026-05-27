package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "record_media",
    indexes = [
        Index(name = "udx_record_media_record_id_sort_order", columnList = "recordId, sortOrder", unique = true),
    ],
)
class RecordMediaEntity(
    val recordId: Long,
    val mediaAssetId: Long,
    val sortOrder: Int,
) : BaseEntity()
