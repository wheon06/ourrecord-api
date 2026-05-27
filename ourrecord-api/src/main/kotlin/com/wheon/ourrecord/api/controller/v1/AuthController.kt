package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.support.response.ApiResponse
import com.wheon.ourrecord.domain.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

class AuthController {
    @PostMapping("/v1/auth/kakao")
    fun loginWithKakao(
        @RequestParam code: String,
    ): ApiResponse<Any> {
        return ApiResponse.success()
    }

    @PostMapping("/v1/auth/refresh")
    fun refreshToken(
        @RequestParam refreshToken: String,
    ): ApiResponse<Any> {
        return ApiResponse.success()
    }

    @PostMapping("/v1/auth/logout")
    fun logout(
        user: User,
    ): ApiResponse<Any> {
        return ApiResponse.success()
    }
}
