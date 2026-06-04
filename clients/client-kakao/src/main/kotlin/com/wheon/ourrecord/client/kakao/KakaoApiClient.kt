package com.wheon.ourrecord.client.kakao

import com.wheon.ourrecord.client.kakao.model.KakaoClientProfileResult
import org.springframework.stereotype.Component

@Component
class KakaoApiClient internal constructor(
    private val kakaoApi: KakaoApi,
) {
    fun getProfile(accessToken: String): KakaoClientProfileResult {
        return kakaoApi.getProfile("Bearer $accessToken", "[\"kakao_account.name\"]").toResult()
    }
}
