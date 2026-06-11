package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.RecordState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecordRepository : JpaRepository<RecordEntity, Long> {
    fun findByIdAndCoupleIdAndStatus(id: Long, coupleId: Long, status: EntityStatus): RecordEntity?

    @Query(
        """
        SELECT new com.wheon.ourrecord.storage.db.core.CouplePlaceRecordRow(
            recordEntity.id,
            recordEntity.title,
            recordEntity.content,
            recordEntity.visitedOn,
            author.id,
            author.displayName,
            author.emoji,
            couplePlace.id,
            place.id,
            couplePlace.categoryCode,
            place.name,
            place.address,
            place.roadAddress,
            place.latitude,
            place.longitude,
            recordEntity.createdAt,
            recordEntity.updatedAt
        )
        FROM RecordEntity recordEntity
        JOIN CoupleMemberEntity author
            ON author.id = recordEntity.authorMemberId
            AND author.coupleId = recordEntity.coupleId
        JOIN CouplePlaceEntity couplePlace
            ON couplePlace.id = recordEntity.couplePlaceId
            AND couplePlace.coupleId = recordEntity.coupleId
            AND couplePlace.status = :status
        JOIN PlaceEntity place
            ON place.id = couplePlace.placeId
            AND place.status = :status
        WHERE recordEntity.coupleId = :coupleId
            AND recordEntity.couplePlaceId = :couplePlaceId
            AND recordEntity.status = :status
            AND recordEntity.state = :recordState
        ORDER BY recordEntity.visitedOn DESC, recordEntity.id DESC
        """,
    )
    fun findCouplePlaceRecordRows(
        coupleId: Long,
        couplePlaceId: Long,
        status: EntityStatus,
        recordState: RecordState,
    ): List<CouplePlaceRecordRow>
}
