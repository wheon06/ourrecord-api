package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.MediaAssetState
import com.wheon.ourrecord.core.enums.RecordState
import com.wheon.ourrecord.storage.db.core.RecordMediaRepository
import com.wheon.ourrecord.support.file.MediaAssetUrlResolver
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class RecordMediaReader(
    private val recordMediaRepository: RecordMediaRepository,
    private val mediaAssetUrlResolver: MediaAssetUrlResolver,
) {
    fun getLatestRecordFirstThumbnails(coupleId: Long, couplePlaceIds: List<Long>): List<CouplePlaceThumbnail> {
        if (couplePlaceIds.isEmpty()) return emptyList()

        return recordMediaRepository.findLatestRecordFirstThumbnailsByCouplePlaceIds(
            coupleId = coupleId,
            couplePlaceIds = couplePlaceIds.distinct(),
        ).map {
            CouplePlaceThumbnail(
                couplePlaceId = it.getCouplePlaceId(),
                thumbnailUrl = mediaAssetUrlResolver.resolve(it.getBucket(), it.getObjectKey()),
            )
        }
    }

    fun getRecordImages(coupleId: Long, recordIds: List<Long>): Map<Long, List<RecordImage>> {
        if (recordIds.isEmpty()) return emptyMap()

        return recordMediaRepository.findRecordImageRows(
            coupleId = coupleId,
            recordIds = recordIds.distinct(),
            status = EntityStatus.ACTIVE,
            mediaAssetState = MediaAssetState.READY,
        ).groupBy(
            keySelector = { it.recordId },
            valueTransform = {
                val fileUrl = mediaAssetUrlResolver.resolve(
                    bucket = it.bucket,
                    objectKey = it.objectKey,
                )

                RecordImage(
                    imageId = it.mediaAssetId,
                    fileUrl = fileUrl,
                    thumbnailUrl = fileUrl,
                    sortOrder = it.sortOrder,
                    width = it.width,
                    height = it.height,
                )
            },
        )
    }

    fun getTimeline(coupleId: Long, size: Int?, cursor: String?): RecordTimeline {
        val normalizedSize = RecordTimelinePolicy.normalizeSize(size)
        val decodedCursor = RecordTimelineCursor.decode(cursor)
        val rows = recordMediaRepository.findTimelineRows(
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
            recordState = RecordState.PUBLISHED,
            mediaAssetState = MediaAssetState.READY,
            cursorVisitedOn = decodedCursor?.visitedOn,
            cursorRecordId = decodedCursor?.recordId,
            cursorSortOrder = decodedCursor?.sortOrder,
            pageable = PageRequest.of(0, normalizedSize + RecordTimelinePolicy.EXTRA_ROW_SIZE),
        )
        val hasNext = rows.size > normalizedSize
        val items = rows.take(normalizedSize).map {
            val fileUrl = mediaAssetUrlResolver.resolve(
                bucket = it.bucket,
                objectKey = it.objectKey,
            )

            RecordTimelineItem(
                recordId = it.recordId,
                imageId = it.imageId,
                sortOrder = it.sortOrder,
                photoUrl = fileUrl,
                thumbnailUrl = fileUrl,
                visitedOn = it.visitedOn,
                placeName = it.placeName,
                title = it.title,
                authorProfile = RecordAuthorProfile(
                    id = it.authorMemberId,
                    displayName = it.authorDisplayName,
                    emoji = it.authorEmoji,
                ),
                createdAt = it.createdAt,
            )
        }

        return RecordTimeline(
            items = items,
            nextCursor = if (hasNext) items.lastOrNull()?.toCursor() else null,
        )
    }

    private fun RecordTimelineItem.toCursor(): String {
        return RecordTimelineCursor(
            visitedOn = visitedOn,
            recordId = recordId,
            sortOrder = sortOrder,
        ).encode()
    }
}
