package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table("record_media")
class RecordMediaEntity(
    val recordId: Long,
    val coupleId: Long,
    val url: String,
) : BaseEntity()
