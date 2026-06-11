package com.wheon.ourrecord.client.kakao

import com.wheon.ourrecord.client.kakao.model.KakaoClientProfileResult
import org.springframework.stereotype.Component

@Component
class KakaoCoreClient internal constructor(
    private val kakaoCoreApi: KakaoCoreApi,
) {
    fun getProfile(accessToken: String): KakaoClientProfileResult {
        return kakaoCoreApi.getProfile(
            "Bearer $accessToken",
            "[\"kakao_account.name\"]",
        ).toResult()
    }
}
