package com.wheon.ourrecord.core.domain.feedback

import com.wheon.ourrecord.storage.db.core.FeedbackEntity
import com.wheon.ourrecord.storage.db.core.FeedbackRepository
import org.springframework.stereotype.Component

@Component
class FeedbackAdder(
    private val feedbackRepository: FeedbackRepository,
) {
    fun add(userId: Long, content: String): Long {
        return feedbackRepository.save(
            FeedbackEntity(
                userId = userId,
                content = content,
            ),
        ).id
    }
}
