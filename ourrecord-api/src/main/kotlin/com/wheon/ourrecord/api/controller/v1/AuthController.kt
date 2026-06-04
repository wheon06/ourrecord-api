package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.KakaoLoginRequest
import com.wheon.ourrecord.api.controller.v1.response.AuthTokenResponse
import com.wheon.ourrecord.api.controller.v1.response.LoginResponse
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.auth.LoginService
import com.wheon.ourrecord.support.auth.RefreshService
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val loginService: LoginService,
    private val refreshService: RefreshService,
) {
    @PostMapping("/v1/auth/kakao")
    fun loginWithKakao(
        @RequestBody request: KakaoLoginRequest,
    ): ApiResponse<LoginResponse> {
        val result = loginService.loginWithKakao(
            code = request.code,
            deviceInstallId = request.device.installId,
            devicePlatform = request.device.platform,
            devicePushToken = request.device.pushToken,
            deviceAppVersion = request.device.appVersion,
        )
        return ApiResponse.success(
            LoginResponse(
                userId = result.userId,
                accessToken = result.accessToken,
                refreshToken = result.refreshToken,
            ),
        )
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
