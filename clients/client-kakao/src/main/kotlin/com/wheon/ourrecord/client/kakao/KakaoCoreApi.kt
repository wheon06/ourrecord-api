package com.wheon.ourrecord.client.kakao

import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange
internal interface KakaoCoreApi {
    @PostExchange("/v2/user/me")
    fun getProfile(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestParam("property_keys") propertyKeys: String,
    ): KakaoProfileResponseDto
}
