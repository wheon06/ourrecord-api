package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.RecordRepository
import org.springframework.stereotype.Component

@Component
class RecordReader(
    private val recordRepository: RecordRepository,
    private val recordMediaReader: RecordMediaReader,
) {
    fun getCouplePlaceRecords(coupleId: Long, couplePlaceId: Long): List<CouplePlaceRecord> {
        val recordRows = recordRepository.findCouplePlaceRecordRows(
            coupleId = coupleId,
            couplePlaceId = couplePlaceId,
            status = EntityStatus.ACTIVE,
            recordState = RecordState.PUBLISHED,
        )
        val imageMap = recordMediaReader.getRecordImages(
            coupleId = coupleId,
            recordIds = recordRows.map { it.recordId },
        )

        return recordRows.mapNotNull {
            val images = imageMap[it.recordId].orEmpty()
            if (images.isEmpty()) return@mapNotNull null

            CouplePlaceRecord(
                recordId = it.recordId,
                title = it.title,
                content = it.content,
                visitedOn = it.visitedOn,
                authorProfile = RecordAuthorProfile(
                    id = it.authorMemberId,
                    displayName = it.authorDisplayName,
                    emoji = it.authorEmoji,
                ),
                place = RecordPlace(
                    couplePlaceId = it.couplePlaceId,
                    placeId = it.placeId,
                    categoryCode = it.categoryCode,
                    name = it.placeName,
                    address = it.address,
                    roadAddress = it.roadAddress,
                    latitude = it.latitude,
                    longitude = it.longitude,
                ),
                images = images,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
            )
        }
    }
}
