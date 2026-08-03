package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.Page
import com.wheon.ourrecord.storage.db.core.RecordRepository
import org.springframework.stereotype.Component

@Component
class RecordFinder(
    private val recordRepository: RecordRepository,
) {
    fun find(spaceId: Long, placeId: Long, offsetLimit: OffsetLimit): Page<Record> {
        val result = recordRepository.findBySpaceIdAndPlaceIdOrderByVisitedOnDesc(
            spaceId = spaceId,
            placeId = placeId,
            pageable = offsetLimit.toPageable(),
        )
        val records = result.map {
            Record(
                id = it.id,
                memberId = it.memberId,
                placeId = it.placeId,
                thumbnailUrl = it.thumbnailUrl,
                title = it.title,
                content = it.content,
                visitedOn = it.visitedOn,
            )
        }

        return Page(records.content, result.hasNext())
    }
}
