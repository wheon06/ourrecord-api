package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.api.assembler.UserAssembler
import com.wheon.ourrecord.core.api.controller.v1.response.MeResponse
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userAssembler: UserAssembler,
) {
    @GetMapping("/api/v1/users/me")
    fun getMeInfo(user: User): ApiResponse<MeResponse> {
        return ApiResponse.success(
            userAssembler.getMe(user),
        )
    }
}
