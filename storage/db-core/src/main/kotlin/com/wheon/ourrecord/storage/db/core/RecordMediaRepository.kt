package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.MediaAssetState
import com.wheon.ourrecord.core.enums.RecordState
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface RecordMediaRepository : JpaRepository<RecordMediaEntity, Long> {
    @Modifying
    @Query(
        """
        DELETE FROM RecordMediaEntity recordMedia
        WHERE recordMedia.recordId = :recordId
            AND recordMedia.coupleId = :coupleId
        """,
    )
    fun deleteByRecordIdAndCoupleId(recordId: Long, coupleId: Long)

    @Query(
        value = """
            SELECT
                latest_record.couple_place_id AS "couplePlaceId",
                media_asset.bucket AS "bucket",
                media_asset.object_key AS "objectKey"
            FROM (
                SELECT
                    record_entity.id,
                    record_entity.couple_place_id,
                    ROW_NUMBER() OVER (
                        PARTITION BY record_entity.couple_place_id
                        ORDER BY record_entity.visited_on DESC, record_entity.id DESC
                    ) AS row_number
                FROM "record" record_entity
                WHERE record_entity.couple_id = :coupleId
                    AND record_entity.couple_place_id IN (:couplePlaceIds)
                    AND record_entity.status = 'ACTIVE'
                    AND record_entity.state = 'PUBLISHED'
            ) latest_record
            JOIN record_media record_media
                ON record_media.record_id = latest_record.id
                AND record_media.couple_id = :coupleId
                AND record_media.sort_order = 1
                AND record_media.status = 'ACTIVE'
            JOIN media_asset media_asset
                ON media_asset.id = record_media.media_asset_id
                AND media_asset.couple_id = :coupleId
                AND media_asset.status = 'ACTIVE'
                AND media_asset.state = 'READY'
            WHERE latest_record.row_number = 1
        """,
        nativeQuery = true,
    )
    fun findLatestRecordFirstThumbnailsByCouplePlaceIds(
        coupleId: Long,
        couplePlaceIds: List<Long>,
    ): List<CouplePlaceThumbnailRow>

    @Query(
        """
        SELECT new com.wheon.ourrecord.storage.db.core.RecordImageRow(
            recordMedia.recordId,
            mediaAsset.id,
            recordMedia.sortOrder,
            mediaAsset.bucket,
            mediaAsset.objectKey,
            mediaAsset.width,
            mediaAsset.height
        )
        FROM RecordMediaEntity recordMedia
        JOIN MediaAssetEntity mediaAsset
            ON mediaAsset.id = recordMedia.mediaAssetId
            AND mediaAsset.coupleId = recordMedia.coupleId
            AND mediaAsset.status = :status
            AND mediaAsset.state = :mediaAssetState
        WHERE recordMedia.coupleId = :coupleId
            AND recordMedia.recordId IN :recordIds
            AND recordMedia.status = :status
        ORDER BY recordMedia.recordId ASC, recordMedia.sortOrder ASC
        """,
    )
    fun findRecordImageRows(
        coupleId: Long,
        recordIds: List<Long>,
        status: EntityStatus,
        mediaAssetState: MediaAssetState,
    ): List<RecordImageRow>

    @Query(
        """
        SELECT new com.wheon.ourrecord.storage.db.core.RecordTimelineRow(
            recordEntity.id,
            mediaAsset.id,
            recordMedia.sortOrder,
            mediaAsset.bucket,
            mediaAsset.objectKey,
            recordEntity.visitedOn,
            place.name,
            recordEntity.title,
            author.id,
            author.displayName,
            author.emoji,
            recordEntity.createdAt
        )
        FROM RecordMediaEntity recordMedia
        JOIN RecordEntity recordEntity
            ON recordEntity.id = recordMedia.recordId
            AND recordEntity.coupleId = recordMedia.coupleId
            AND recordEntity.status = :status
            AND recordEntity.state = :recordState
        JOIN MediaAssetEntity mediaAsset
            ON mediaAsset.id = recordMedia.mediaAssetId
            AND mediaAsset.coupleId = recordMedia.coupleId
            AND mediaAsset.status = :status
            AND mediaAsset.state = :mediaAssetState
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
        WHERE recordMedia.coupleId = :coupleId
            AND recordMedia.status = :status
            AND (
                :cursorVisitedOn IS NULL
                OR recordEntity.visitedOn < :cursorVisitedOn
                OR (
                    recordEntity.visitedOn = :cursorVisitedOn
                    AND recordEntity.id < :cursorRecordId
                )
                OR (
                    recordEntity.visitedOn = :cursorVisitedOn
                    AND recordEntity.id = :cursorRecordId
                    AND recordMedia.sortOrder > :cursorSortOrder
                )
            )
        ORDER BY recordEntity.visitedOn DESC, recordEntity.id DESC, recordMedia.sortOrder ASC
        """,
    )
    fun findTimelineRows(
        coupleId: Long,
        status: EntityStatus,
        recordState: RecordState,
        mediaAssetState: MediaAssetState,
        cursorVisitedOn: LocalDate?,
        cursorRecordId: Long?,
        cursorSortOrder: Int?,
        pageable: Pageable,
    ): List<RecordTimelineRow>
}
