package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.domain.couple.CoupleService
import com.wheon.ourrecord.domain.user.User
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CoupleController(
    private val coupleService: CoupleService,
) {
    @PostMapping("/v1/couple/{inviteKey}/accept")
    fun acceptInvite(user: User, @PathVariable inviteKey: String): ApiResponse<Any> {
        coupleService.accept(user, inviteKey)
        return ApiResponse.success()
    }
}
