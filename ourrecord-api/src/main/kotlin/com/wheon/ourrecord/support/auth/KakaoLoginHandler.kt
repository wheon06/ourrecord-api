package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.client.kakao.KakaoCoreClient
import com.wheon.ourrecord.core.enums.IdentityProviderType
import org.springframework.stereotype.Component

@Component
class KakaoLoginHandler(
    private val kakaoCoreClient: KakaoCoreClient,
) {
    fun getProfile(accessToken: String): SocialProfile {
        val kakaoProfile = kakaoCoreClient.getProfile(accessToken)

        return SocialProfile(
            provider = IdentityProviderType.KAKAO,
            providerUserId = kakaoProfile.id,
            name = kakaoProfile.name,
        )
    }
}
