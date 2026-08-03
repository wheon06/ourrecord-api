package com.wheon.ourrecord.core.domain.feedback

import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class FeedbackService(
    private val feedbackAdder: FeedbackAdder,
) {
    fun addFeedback(user: User, content: String): Long {
        return feedbackAdder.add(user.id, content)
    }
}
