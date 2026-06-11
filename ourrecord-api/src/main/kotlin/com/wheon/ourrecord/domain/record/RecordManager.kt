package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.MediaAssetState
import com.wheon.ourrecord.core.enums.RecordState
import com.wheon.ourrecord.storage.db.core.MediaAssetEntity
import com.wheon.ourrecord.storage.db.core.MediaAssetRepository
import com.wheon.ourrecord.storage.db.core.RecordEntity
import com.wheon.ourrecord.storage.db.core.RecordMediaEntity
import com.wheon.ourrecord.storage.db.core.RecordMediaRepository
import com.wheon.ourrecord.storage.db.core.RecordRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import com.wheon.ourrecord.support.file.ImageHandle
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RecordManager(
    private val recordRepository: RecordRepository,
    private val mediaAssetRepository: MediaAssetRepository,
    private val recordMediaRepository: RecordMediaRepository,
) {
    @Transactional
    fun create(newRecord: NewRecord, imageHandle: ImageHandle): Long {
        val saved = recordRepository.save(
            RecordEntity(
                coupleId = newRecord.coupleId,
                authorMemberId = newRecord.authorMemberId,
                couplePlaceId = newRecord.couplePlaceId,
                title = newRecord.title,
                content = newRecord.content,
                visitedOn = newRecord.visitedOn,
                state = RecordState.PUBLISHED,
            ),
        )

        saveRecordMedia(
            coupleId = saved.coupleId,
            recordId = saved.id,
            imageIds = imageHandle.addImageIds,
        )

        return saved.id
    }

    @Transactional
    fun update(coupleId: Long, recordId: Long, couplePlaceId: Long, updateRecord: UpdateRecord) {
        val record = recordRepository.findByIdAndCoupleIdAndStatus(
            id = recordId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
        ) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        record.update(
            couplePlaceId = couplePlaceId,
            title = updateRecord.title,
            content = updateRecord.content,
            visitedOn = updateRecord.visitedOn,
        )

        recordMediaRepository.deleteByRecordIdAndCoupleId(
            recordId = record.id,
            coupleId = record.coupleId,
        )

        saveRecordMedia(
            coupleId = record.coupleId,
            recordId = record.id,
            imageIds = updateRecord.imageIds,
        )
    }

    @Transactional
    fun updateDetails(coupleId: Long, recordId: Long, updateRecordDetails: UpdateRecordDetails) {
        val record = recordRepository.findByIdAndCoupleIdAndStatus(
            id = recordId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
        ) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        record.update(
            couplePlaceId = record.couplePlaceId,
            title = updateRecordDetails.title,
            content = updateRecordDetails.content,
            visitedOn = updateRecordDetails.visitedOn,
        )
    }

    @Transactional
    fun delete(coupleId: Long, recordId: Long) {
        val record = recordRepository.findByIdAndCoupleIdAndStatus(
            id = recordId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
        ) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        record.archived()
        record.delete()
    }

    private fun saveRecordMedia(coupleId: Long, recordId: Long, imageIds: List<Long>) {
        val mediaAssetMap = getVerifiedMediaAssetMap(
            coupleId = coupleId,
            imageIds = imageIds,
        )

        recordMediaRepository.saveAll(
            imageIds.mapIndexed { index, mediaAssetId ->
                RecordMediaEntity(
                    recordId = recordId,
                    coupleId = coupleId,
                    mediaAssetId = mediaAssetMap.getValue(mediaAssetId).id,
                    sortOrder = index + 1,
                )
            },
        )
    }

    private fun getVerifiedMediaAssetMap(coupleId: Long, imageIds: List<Long>): Map<Long, MediaAssetEntity> {
        if (imageIds.size < RecordImagePolicy.MIN_IMAGE_COUNT) {
            throw ApiException(ErrorType.INVALID_REQUEST)
        }
        if (imageIds.size > RecordImagePolicy.MAX_IMAGE_COUNT) {
            throw ApiException(ErrorType.INVALID_REQUEST)
        }

        val distinctImageIds = imageIds.distinct()
        if (distinctImageIds.size != imageIds.size) {
            throw ApiException(ErrorType.INVALID_REQUEST)
        }

        val mediaAssets = mediaAssetRepository.findByCoupleIdAndIdInAndStateAndStatus(
            coupleId = coupleId,
            ids = distinctImageIds,
            state = MediaAssetState.READY,
            status = EntityStatus.ACTIVE,
        )

        if (mediaAssets.size != distinctImageIds.size) {
            throw ApiException(ErrorType.INVALID_REQUEST)
        }

        return mediaAssets.associateBy { it.id }
    }
}
