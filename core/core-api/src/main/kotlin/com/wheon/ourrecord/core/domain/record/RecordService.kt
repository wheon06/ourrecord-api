package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.user.CoupleUser
import org.springframework.stereotype.Service

@Service
class RecordService(
    private val recordManager: RecordManager,
) {
    fun create(
        coupleUser: CoupleUser,
        target: RecordTarget,
        content: RecordContent,
        media: List<NewRecordMedia>,
    ): Long {
        return recordManager.create(
            coupleUser = coupleUser,
            target = target,
            content = content,
            media = media,
        )
    }
}
