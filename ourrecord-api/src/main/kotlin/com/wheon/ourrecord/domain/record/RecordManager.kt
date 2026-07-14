package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.domain.user.CoupleUser
import com.wheon.ourrecord.storage.db.core.CouplePlaceRepository
import com.wheon.ourrecord.storage.db.core.RecordEntity
import com.wheon.ourrecord.storage.db.core.RecordMediaEntity
import com.wheon.ourrecord.storage.db.core.RecordMediaRepository
import com.wheon.ourrecord.storage.db.core.RecordRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RecordManager(
    private val recordRepository: RecordRepository,
    private val recordMediaRepository: RecordMediaRepository,
    private val couplePlaceRepository: CouplePlaceRepository,
) {
    @Transactional
    fun create(
        coupleUser: CoupleUser,
        target: RecordTarget,
        content: RecordContent,
        media: List<NewRecordMedia>,
    ): Long {
        val couplePlace = couplePlaceRepository.findByIdAndCoupleIdAndStatus(
            id = target.targetId,
            coupleId = coupleUser.coupleId,
            status = EntityStatus.ACTIVE,
        ) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        val savedRecord = recordRepository.save(
            RecordEntity(
                userId = coupleUser.userId,
                coupleId = coupleUser.coupleId,
                couplePlaceId = couplePlace.id,
                title = content.title,
                content = content.content,
                visitedOn = content.visitedOn,
            ),
        )

        recordMediaRepository.saveAll(
            media.map {
                RecordMediaEntity(
                    recordId = savedRecord.id,
                    coupleId = coupleUser.coupleId,
                    url = it.url,
                )
            },
        )

        return savedRecord.id
    }
}
