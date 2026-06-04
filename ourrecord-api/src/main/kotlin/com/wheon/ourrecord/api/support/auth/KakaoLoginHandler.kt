package com.wheon.ourrecord.api.support.auth

import com.wheon.ourrecord.client.kakao.KakaoApiClient
import com.wheon.ourrecord.client.kakao.KakaoAuthClient
import com.wheon.ourrecord.core.enums.SocialProviderType
import org.springframework.stereotype.Component

@Component
class KakaoLoginHandler(
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoApiClient: KakaoApiClient,
) {
    fun getProfile(code: String): SocialProfile {
        val token = kakaoAuthClient.getToken(code)
        val kakaoProfile = kakaoApiClient.getProfile(token.accessToken)

        return SocialProfile(
            provider = SocialProviderType.KAKAO,
            providerUserId = kakaoProfile.id,
            name = kakaoProfile.name,
        )
    }
}
