package com.wheon.ourrecord.client.naver

import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
internal interface NaverApi {
    @GetExchange("/v1/search/local.json")
    fun searchPlace(
        @RequestParam query: String,
        @RequestParam display: Int = 5,
        @RequestParam sort: String = "random",
    ): NaverPlaceResponseDto
}
