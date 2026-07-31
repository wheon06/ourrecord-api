package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.domain.record.Record
import com.wheon.ourrecord.core.domain.record.RecordAddPostProcess
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.MetaPlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PlaceRecordAddProcessor(
    private val placeRepository: PlaceRepository,
    private val metaPlaceRepository: MetaPlaceRepository,
) : RecordAddPostProcess {
    @Transactional
    override fun process(user: User, record: Record) {
        placeRepository.findByIdAndStatus(record.placeId, EntityStatus.ACTIVE)
            ?.applyThumbnailUrl(record.thumbnailUrl)
        metaPlaceRepository.findByPlaceIdAndStatus(record.placeId, EntityStatus.ACTIVE)
            ?.let {
                it.applyRecordCount(it.recordCount + 1)
                it.applyLastVisitedAt(record.visitedOn)
            }
    }
}
