package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "record_media")
class RecordMediaEntity(
    val recordId: Long,
    val coupleId: Long,
    val mediaUrl: String,
) : BaseEntity()
