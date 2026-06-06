package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.response.PlaceCategoryResponse
import com.wheon.ourrecord.api.controller.v1.response.PlaceSearchResponse
import com.wheon.ourrecord.client.naver.NaverApiClient
import com.wheon.ourrecord.domain.place.PlaceService
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController(
    private val placeService: PlaceService,
    private val naverApiClient: NaverApiClient,
) {
    @GetMapping("/v1/places/search")
    fun searchPlace(
        apiUser: ApiUser,
        @RequestParam keyword: String,
    ): ApiResponse<List<PlaceSearchResponse>> {
        val result = naverApiClient.searchPlace(keyword)
        return ApiResponse.success(PlaceSearchResponse.of(result.items))
    }

    @GetMapping("/v1/place-categories")
    fun getPlaceCategories(
        apiUser: ApiUser,
    ): ApiResponse<List<PlaceCategoryResponse>> {
        val categories = placeService.getPlaceCategories()
        return ApiResponse.success(PlaceCategoryResponse.of(categories))
    }
}
