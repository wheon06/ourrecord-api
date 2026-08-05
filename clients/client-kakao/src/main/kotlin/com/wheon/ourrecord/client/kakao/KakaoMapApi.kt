package com.wheon.ourrecord.client.kakao

import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
internal interface KakaoMapApi {
    @GetExchange("/v2/local/search/keyword.json")
    fun searchPlace(
        @RequestParam query: String,
        @RequestParam page: Int = 1,
        @RequestParam size: Int = 10,
    ): KakaoPlaceResponseDto
}
