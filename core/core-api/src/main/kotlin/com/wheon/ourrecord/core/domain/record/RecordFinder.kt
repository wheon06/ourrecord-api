package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.support.Page
import com.wheon.ourrecord.storage.db.core.RecordRepository
import org.springframework.stereotype.Component

@Component
class RecordFinder(
    private val recordRepository: RecordRepository,
    private val recordMediaFinder: RecordMediaFinder,
) {
    fun find(spaceId: Long, lastRecordId: Long?): Page<Record> {
        val found = recordRepository.findBySpaceIdAndIdGreaterThanOrderByCreatedAtDesc(
            spaceId = spaceId,
            id = lastRecordId,
        )

        return Page(
            listOf(),
            false,
        )
    }
}
