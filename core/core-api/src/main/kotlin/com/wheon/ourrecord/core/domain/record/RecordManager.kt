package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.space.SpaceContext
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
        media: List<NewRecordMedia>,
    ): Long {
        val savedRecord = recordRepository.save(
            RecordEntity(
                spaceId = context.spaceId,
                memberId = context.memberId,
                placeId = target.targetId,
                title = content.title,
                content = content.content,
                visitedOn = content.visitedOn,
            ),
        )

        recordMediaRepository.saveAll(
            media.map {
                RecordMediaEntity(
                    recordId = savedRecord.id,
                    spaceId = context.spaceId,
                    mediaUrl = it.url,
                )
            },
        )

        return savedRecord.id
    }
}
