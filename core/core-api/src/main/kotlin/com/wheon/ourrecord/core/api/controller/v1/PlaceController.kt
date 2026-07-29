package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.client.kakao.KakaoMapClient
import com.wheon.ourrecord.core.api.assembler.PlaceAssembler
import com.wheon.ourrecord.core.api.controller.v1.request.AddPlaceRequest
import com.wheon.ourrecord.core.api.controller.v1.response.PlaceSearchResultResponse
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController(
    private val placeAssembler: PlaceAssembler,
    private val kakaoMapClient: KakaoMapClient,
) {
    @PostMapping("/api/v1/places/search")
    fun searchPlace(
        user: User,
        @RequestParam keyword: String,
    ): ApiResponse<PlaceSearchResultResponse> {
        val result = kakaoMapClient.searchPlace(keyword)
        return ApiResponse.success(
            PlaceSearchResultResponse.of(result),
        )
    }

    @PostMapping("/api/v1/places")
    fun addPlace(
        user: User,
        @RequestBody request: AddPlaceRequest,
    ): ApiResponse<Long> {
        val successId = placeAssembler.addPlace(user, request.toNewPlace())
        return ApiResponse.success(successId)
    }
}
