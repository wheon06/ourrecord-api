package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.client.kakao.KakaoAuthClient
import com.wheon.ourrecord.domain.UserSessionManager
import com.wheon.ourrecord.support.auth.token.TokenManager
import org.springframework.stereotype.Service

@Service
class SnsLoginService(
    private val kakaoLoginHandler: KakaoLoginHandler,
    private val socialLoginHandler: SocialLoginHandler,
    private val userSessionManager: UserSessionManager,
    private val tokenManager: TokenManager,
    private val kakaoAuthClient: KakaoAuthClient,
) {
    fun kakaoLogin(code: String) {
        val kakaoTokenResult = kakaoAuthClient.getToken(code)
    }
}
