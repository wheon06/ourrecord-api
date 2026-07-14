package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.response.AuthTokenResponse
import com.wheon.ourrecord.client.kakao.KakaoAuthClient
import com.wheon.ourrecord.client.kakao.KakaoCoreClient
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.auth.SnsLoginService
import com.wheon.ourrecord.support.auth.RefreshService
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val snsLoginService: SnsLoginService,
    private val refreshService: RefreshService,
) {
    @GetMapping("/sns/login/kakao/callback")
    fun kakaoLoginCallback(
        @RequestParam code: String,
    ): ApiResponse<AuthTokenResponse> {
        snsLoginService.kakaoLogin(code)
    }

    @PostMapping("/v1/auth/refresh")
    fun refreshToken(
        @RequestParam refreshToken: String,
    ): ApiResponse<AuthTokenResponse> {
        val token = refreshService.refresh(refreshToken)
        return ApiResponse.success(
            AuthTokenResponse(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken,
            ),
        )
    }

    @PostMapping("/v1/auth/logout")
    fun logout(
        apiUser: ApiUser,
    ): ApiResponse<Any> {
        return ApiResponse.success()
    }
}
