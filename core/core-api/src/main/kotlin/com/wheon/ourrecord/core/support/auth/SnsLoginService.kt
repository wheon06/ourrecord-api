package com.wheon.ourrecord.core.support.auth

import com.wheon.ourrecord.client.kakao.KakaoAuthClient
import com.wheon.ourrecord.client.kakao.KakaoCoreClient
import com.wheon.ourrecord.core.domain.UserSessionManager
import com.wheon.ourrecord.core.enums.AuthKeyType
import com.wheon.ourrecord.core.enums.IdentityProviderType
import com.wheon.ourrecord.core.support.auth.token.AuthKeyManager
import org.springframework.stereotype.Service

@Service
class SnsLoginService(
    private val userProvisioner: UserProvisioner,
    private val userSessionManager: UserSessionManager,
    private val authKeyManager: AuthKeyManager,
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoCoreClient: KakaoCoreClient,
) {
    fun kakaoLogin(code: String): LoginResult {
        val kakaoTokenResult = kakaoAuthClient.getToken(code)
        val kakaoProfileResult = kakaoCoreClient.getProfile(kakaoTokenResult.accessToken)

        val provisionedUser = userProvisioner.getOrProvision(
            SocialProfile(
                providerType = IdentityProviderType.KAKAO,
                providerUserId = kakaoProfileResult.id,
            ),
        )
        val sessionId = userSessionManager.create(
            userId = provisionedUser.userId,
        )

        return LoginResult(
            userId = provisionedUser.userId,
            accessKey = authKeyManager.issue(provisionedUser.userId, sessionId, AuthKeyType.ACCESS),
            refreshKey = authKeyManager.issue(provisionedUser.userId, sessionId, AuthKeyType.REFRESH),
            isNewUser = provisionedUser.isNewUser,
        )
    }
}
