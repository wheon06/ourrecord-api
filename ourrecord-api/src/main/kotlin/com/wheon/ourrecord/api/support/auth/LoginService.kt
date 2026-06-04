package com.wheon.ourrecord.api.support.auth

import com.wheon.ourrecord.api.support.auth.token.TokenManager
import com.wheon.ourrecord.core.enums.PlatformType
import com.wheon.ourrecord.domain.UserDeviceManager
import com.wheon.ourrecord.domain.UserSessionManager
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val kakaoLoginHandler: KakaoLoginHandler,
    private val socialLoginHandler: SocialLoginHandler,
    private val userDeviceManager: UserDeviceManager,
    private val userSessionManager: UserSessionManager,
    private val tokenManager: TokenManager,
) {
    fun loginWithKakao(
        code: String,
        deviceInstallId: String,
        devicePlatform: PlatformType,
        devicePushToken: String?,
        deviceAppVersion: String,
    ): LoginResult {
        val profile = kakaoLoginHandler.getProfile(code)
        val userId = socialLoginHandler.loginOrSignup(profile)
        val userDeviceId = userDeviceManager.registerOrTouch(
            userId = userId,
            installId = deviceInstallId,
            platform = devicePlatform,
            pushToken = devicePushToken,
            appVersion = deviceAppVersion,
        )
        val token = tokenManager.issue(userId)

        userSessionManager.create(
            userId = userId,
            userDeviceId = userDeviceId,
            refreshToken = token.refreshToken,
            refreshTokenId = token.refreshTokenId,
            expiresAt = token.refreshTokenExpiresAt,
        )

        return LoginResult(
            userId = userId,
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
        )
    }
}
