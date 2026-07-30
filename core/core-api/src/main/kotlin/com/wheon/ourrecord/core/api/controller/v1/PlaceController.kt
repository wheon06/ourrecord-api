package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.client.kakao.KakaoMapClient
import com.wheon.ourrecord.core.api.assembler.PlaceAssembler
import com.wheon.ourrecord.core.api.controller.v1.request.AddPlaceRequest
import com.wheon.ourrecord.core.api.controller.v1.response.PlaceResponse
import com.wheon.ourrecord.core.api.controller.v1.response.PlaceSearchResultResponse
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.response.ApiResponse
import com.wheon.ourrecord.core.support.response.PageResponse
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/api/v1/places")
    fun getPlaces(
        user: User,
        @RequestParam offset: Int?,
        @RequestParam limit: Int?,
    ): ApiResponse<PageResponse<PlaceResponse>> {
        val responses = placeAssembler.getPlaces(user, OffsetLimit(offset, limit))
        return ApiResponse.success(responses)
    }
}
