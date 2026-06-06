package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.client.kakao.KakaoApiClient
import com.wheon.ourrecord.core.enums.SocialProviderType
import org.springframework.stereotype.Component

@Component
class KakaoLoginHandler(
    private val kakaoApiClient: KakaoApiClient,
) {
    fun getProfile(accessToken: String): SocialProfile {
        val kakaoProfile = kakaoApiClient.getProfile(accessToken)

        return SocialProfile(
            provider = SocialProviderType.KAKAO,
            providerUserId = kakaoProfile.id,
            name = kakaoProfile.name,
        )
    }
}
