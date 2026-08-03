package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.client.kakao.KakaoAuthClient
import com.wheon.ourrecord.core.domain.UserSessionManager
import com.wheon.ourrecord.core.enums.AuthKeyType
import com.wheon.ourrecord.core.enums.IdentityProviderType
import com.wheon.ourrecord.core.support.auth.LoginResult
import com.wheon.ourrecord.core.support.auth.SnsLoginService
import com.wheon.ourrecord.core.support.auth.SocialProfile
import com.wheon.ourrecord.core.support.auth.UserProvisioner
import com.wheon.ourrecord.core.support.auth.token.AuthKeyManager
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val snsLoginService: SnsLoginService,
    private val kakaoAuthClient: KakaoAuthClient,
    private val userProvisioner: UserProvisioner, // DEV
    private val userSessionManager: UserSessionManager, // DEV
    private val authKeyManager: AuthKeyManager, // DEV
) {
    val log = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/api/v1/auth/kakao")
    fun loginWithKakao(
        @RequestParam accessToken: String,
    ): ApiResponse<LoginResult> {
        return ApiResponse.success(
            snsLoginService.kakaoLogin(accessToken),
        )
    }

    @GetMapping("/api/v1/auth/kakao/callback")
    fun loginWithKakaoCallback(
        @RequestParam code: String,
    ): ApiResponse<LoginResult> {
        val kakaoTokenResult = kakaoAuthClient.getToken(code)
        return ApiResponse.success(
            snsLoginService.kakaoLogin(kakaoTokenResult.accessToken),
        )
    }

    @GetMapping("/api/v1/auth/dev-login")
    fun devLogin(
        @RequestParam key: String,
    ): ApiResponse<LoginResult> {
        val provisionedUser = userProvisioner.getOrProvision(
            SocialProfile(
                providerType = IdentityProviderType.DEBUG_DEV,
                providerUserId = key,
            ),
        )
        val sessionId = userSessionManager.create(
            userId = provisionedUser.userId,
        )

        return ApiResponse.success(
            LoginResult(
                userId = provisionedUser.userId,
                accessKey = authKeyManager.issue(provisionedUser.userId, sessionId, AuthKeyType.ACCESS),
                refreshKey = authKeyManager.issue(provisionedUser.userId, sessionId, AuthKeyType.REFRESH),
                isNewUser = provisionedUser.isNewUser,
            ),
        )
    }
}
