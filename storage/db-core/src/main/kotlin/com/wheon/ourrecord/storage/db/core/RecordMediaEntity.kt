package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "record_media")
class RecordMediaEntity(
    val recordId: Long,
    val spaceId: Long,
    @Column(columnDefinition = "TEXT")
    val mediaUrl: String,
) : BaseEntity()
