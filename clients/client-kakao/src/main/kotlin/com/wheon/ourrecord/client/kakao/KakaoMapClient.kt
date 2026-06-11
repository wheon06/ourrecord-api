package com.wheon.ourrecord.client.kakao

import com.wheon.ourrecord.client.kakao.model.KakaoClientPlaceResult
import org.springframework.stereotype.Component

@Component
class KakaoMapClient internal constructor(
    private val kakaoMapApi: KakaoMapApi,
) {
    fun searchPlace(
        keyword: String,
    ): KakaoClientPlaceResult {
        return kakaoMapApi.searchPlace(keyword).toResult()
    }
}
