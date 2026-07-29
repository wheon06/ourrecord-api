package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.client.kakao.KakaoMapClient
import com.wheon.ourrecord.core.api.controller.v1.response.PlaceSearchResultResponse
import com.wheon.ourrecord.core.domain.place.PlaceService
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController(
    private val placeService: PlaceService,
    private val kakaoMapClient: KakaoMapClient,
) {
    @PostMapping("/api/v1/places/search")
    fun searchPlace(
        @RequestParam keyword: String,
    ): ApiResponse<PlaceSearchResultResponse> {
        val result = kakaoMapClient.searchPlace(keyword)
        return ApiResponse.success(
            PlaceSearchResultResponse.of(result),
        )
    }
}
