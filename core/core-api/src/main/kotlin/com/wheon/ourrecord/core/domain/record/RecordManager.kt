package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.space.SpaceContext
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.core.support.file.MediaHandle
import com.wheon.ourrecord.storage.db.core.RecordEntity
import com.wheon.ourrecord.storage.db.core.RecordMediaEntity
import com.wheon.ourrecord.storage.db.core.RecordMediaRepository
import com.wheon.ourrecord.storage.db.core.RecordRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RecordManager(
    private val recordRepository: RecordRepository,
    private val recordMediaRepository: RecordMediaRepository,
) {
    @Transactional
    fun create(
        context: SpaceContext,
        target: RecordTarget,
        content: RecordContent,
        mediaHandle: MediaHandle,
    ): Record {
        if (!mediaHandle.hasMediaToAdd()) throw CoreException(ErrorType.INVALID_REQUEST)
        val savedRecord = recordRepository.save(
            RecordEntity(
                spaceId = context.spaceId,
                memberId = context.memberId,
                placeId = target.targetId,
                thumbnailUrl = mediaHandle.addMediaUrls.first(),
                title = content.title,
                content = content.content,
                visitedOn = content.visitedOn,
            ),
        )

        val savedMedia = recordMediaRepository.saveAll(
            mediaHandle.addMediaUrls.map {
                RecordMediaEntity(
                    recordId = savedRecord.id,
                    spaceId = context.spaceId,
                    mediaUrl = it,
                )
            },
        )

        return Record(
            id = savedRecord.id,
            memberId = savedRecord.memberId,
            placeId = savedRecord.placeId,
            thumbnailUrl = savedMedia.first().mediaUrl,
            title = savedRecord.title,
            content = savedRecord.content,
            visitedOn = savedRecord.visitedOn,
        )
    }

    @Transactional
    fun update(
        context: SpaceContext,
        recordId: Long,
        content: RecordContent,
        mediaHandle: MediaHandle,
    ): Record {
        val found = recordRepository.findByIdAndMemberId(recordId, context.memberId) ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        found.updateContent(
            content.title,
            content.content,
            content.visitedOn,
        )

        val existingMedia = recordMediaRepository.findByRecordIdAndStatus(recordId, EntityStatus.ACTIVE)

        if (mediaHandle.hasMediaToDelete()) {
            existingMedia.filter { it.mediaUrl in mediaHandle.deleteMediaUrls }
                .forEach { it.delete() }
        }

        if (mediaHandle.hasMediaToAdd()) {
            recordMediaRepository.saveAll(
                mediaHandle.addMediaUrls.map {
                    RecordMediaEntity(
                        spaceId = found.spaceId,
                        recordId = found.id,
                        mediaUrl = it,
                    )
                },
            )
        }

        val recordMedia = recordMediaRepository.findByRecordIdAndStatus(found.id, EntityStatus.ACTIVE)
        if (recordMedia.isEmpty()) throw CoreException(ErrorType.RECORD_CANNOT_DELETE_ALL_IMAGES)
        found.applyThumbnailUrl(recordMedia.minByOrNull { it.id }!!.mediaUrl)

        return Record(
            id = found.id,
            memberId = found.memberId,
            placeId = found.placeId,
            thumbnailUrl = found.thumbnailUrl,
            title = found.title,
            content = found.content,
            visitedOn = found.visitedOn,
        )
    }
}
