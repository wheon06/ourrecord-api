package com.wheon.ourrecord.auth.domain

import com.wheon.ourrecord.client.kakao.KakaoClient
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val kakaoClient: KakaoClient,
) {
    fun loginWithKakao(code: String) {
        val result = kakaoClient.getToken(code)
    }
}
