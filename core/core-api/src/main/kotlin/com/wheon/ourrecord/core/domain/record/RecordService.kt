package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.space.SpaceVerifier
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.Page
import org.springframework.stereotype.Service

@Service
class RecordService(
    private val recordFinder: RecordFinder,
    private val recordManager: RecordManager,
    private val spaceVerifier: SpaceVerifier,
    private val recordAddPostProcessor: List<RecordAddPostProcess>,
) {
    fun create(
        user: User,
        spaceId: Long,
        target: RecordTarget,
        content: RecordContent,
        media: List<NewRecordMedia>,
    ): Long {
        val context = spaceVerifier.verify(user.id, spaceId)
        val record = recordManager.create(
            context = context,
            target = target,
            content = content,
            media = media,
        )
        recordAddPostProcessor.forEach { it.process(user, record) }
        return record.id
    }

    fun getRecords(spaceId: Long, lastRecordId: Long?): Page<Record> {
        return recordFinder.find(spaceId, lastRecordId)
    }
}
