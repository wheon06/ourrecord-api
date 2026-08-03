package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.domain.record.Record
import com.wheon.ourrecord.core.domain.record.RecordModifyPostProcess
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PlaceRecordModifyProcessor(
    private val placeRepository: PlaceRepository,
) : RecordModifyPostProcess {
    @Async
    @Transactional
    override fun process(user: User, record: Record) {
        placeRepository.findByIdAndStatus(record.placeId, EntityStatus.ACTIVE)
            ?.applyThumbnailUrl(record.thumbnailUrl)
    }
}
