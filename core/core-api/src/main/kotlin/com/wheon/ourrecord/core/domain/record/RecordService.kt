package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.space.SpaceVerifier
import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class RecordService(
    private val recordManager: RecordManager,
    private val spaceVerifier: SpaceVerifier,
) {
    fun create(
        user: User,
        spaceId: Long,
        target: RecordTarget,
        content: RecordContent,
        media: List<NewRecordMedia>,
    ): Long {
        val context = spaceVerifier.verify(user.id, spaceId)
        return recordManager.create(
            context = context,
            target = target,
            content = content,
            media = media,
        )
    }
}
