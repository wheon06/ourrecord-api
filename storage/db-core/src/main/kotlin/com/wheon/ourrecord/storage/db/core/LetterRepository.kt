package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.LetterState
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LetterRepository : JpaRepository<LetterEntity, Long> {
    @Query(
        """
        SELECT new com.wheon.ourrecord.storage.db.core.LetterRow(
            letter.id,
            COALESCE(sender.displayName, '상대방'),
            letter.content,
            letter.revealAt,
            letter.createdAt,
            letter.coupleId,
            letter.senderUserId
        )
        FROM LetterEntity letter
        LEFT JOIN CoupleMemberEntity sender
            ON sender.userId = letter.senderUserId
            AND sender.coupleId = letter.coupleId
        WHERE letter.coupleId = :coupleId
            AND letter.status = :status
            AND letter.state <> :excludedState
        ORDER BY letter.revealAt DESC, letter.id DESC
        """,
    )
    fun findLetterRows(
        coupleId: Long,
        status: EntityStatus,
        excludedState: LetterState,
        pageable: Pageable,
    ): List<LetterRow>
}
