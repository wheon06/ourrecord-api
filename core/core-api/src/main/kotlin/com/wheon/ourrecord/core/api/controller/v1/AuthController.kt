package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.api.controller.v1.response.AuthKeyResponse
import com.wheon.ourrecord.core.support.auth.SnsLoginService
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val snsLoginService: SnsLoginService,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/api/v1/auth/kakao")
    fun loginWithKakao(
        @RequestParam code: String,
    ): ApiResponse<AuthKeyResponse> {
        val loginResult = snsLoginService.kakaoLogin(code)
        return ApiResponse.success(
            AuthKeyResponse(
                accessKey = loginResult.accessKey,
                refreshKey = loginResult.refreshKey,
            ),
        )
    }
}
