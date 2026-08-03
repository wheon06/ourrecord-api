package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.domain.feedback.FeedbackService
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FeedbackController(
    private val feedbackService: FeedbackService,
) {
    @PostMapping("/api/v1/feedback")
    fun submitFeedback(
        user: User,
        @RequestBody content: String,
    ): ApiResponse<Long> {
        val successId = feedbackService.addFeedback(user, content)
        return ApiResponse.success(successId)
    }
}
