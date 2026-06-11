package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.NotificationState
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<NotificationEntity, Long> {
    fun findByToUserIdAndCoupleIdAndStatusOrderByCreatedAtDesc(
        toUserId: Long,
        coupleId: Long,
        status: EntityStatus,
        pageable: Pageable,
    ): List<NotificationEntity>

    fun findByIdAndToUserIdAndCoupleIdAndStatus(
        id: Long,
        toUserId: Long,
        coupleId: Long,
        status: EntityStatus,
    ): NotificationEntity?

    fun findByToUserIdAndCoupleIdAndStatusAndState(
        toUserId: Long,
        coupleId: Long,
        status: EntityStatus,
        state: NotificationState,
    ): List<NotificationEntity>
}
