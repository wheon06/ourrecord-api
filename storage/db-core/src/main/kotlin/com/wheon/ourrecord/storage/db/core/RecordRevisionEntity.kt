package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

/**
 * 기록 수정 이력 데이터를 위한 테이블입니다.
 * append-only 방식으로 기록 수정 이력을 저장합니다.
 */
@Entity
@Table(name = "record_revision")
class RecordRevisionEntity(
    val recordId: Long,
    val revisionNumber: Int,
    val title: String,
    val content: String,
    val visitedOn: String,
    val changedByUserId: Long,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.MIN,
) : BaseIdEntity()
