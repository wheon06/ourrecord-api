package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.storage.db.core.RecordMediaRepository
import org.springframework.stereotype.Component

@Component
class RecordMediaReader(
    private val recordMediaRepository: RecordMediaRepository,
) {
    fun readMediaMap(records: List<Record>): Map<Long, List<RecordMedia>> {
        return recordMediaRepository.findByRecordIdIn(records.map { it.id })
            .map {
                RecordMedia(
                    id = it.recordId,
                    url = it.mediaUrl,
                )
            }.groupBy { record -> record.id }
    }
}
