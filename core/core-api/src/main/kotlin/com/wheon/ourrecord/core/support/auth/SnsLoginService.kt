package com.wheon.ourrecord.core.support.auth

import com.wheon.ourrecord.client.kakao.KakaoAuthClient
import com.wheon.ourrecord.client.kakao.KakaoCoreClient
import com.wheon.ourrecord.core.domain.UserSessionManager
import com.wheon.ourrecord.core.support.auth.token.AuthKeyManager
import org.springframework.stereotype.Service

@Service
class SnsLoginService(
    private val kakaoLoginHandler: KakaoLoginHandler,
    private val socialLoginHandler: SocialLoginHandler,
    private val userSessionManager: UserSessionManager,
    private val authKeyManager: AuthKeyManager,
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoCoreClient: KakaoCoreClient,
) {
    fun kakaoLogin(code: String) {
        val kakaoTokenResult = kakaoAuthClient.getToken(code)
        val kakaoProfileResult = kakaoCoreClient.getProfile(kakaoTokenResult.accessToken)
    }
}
