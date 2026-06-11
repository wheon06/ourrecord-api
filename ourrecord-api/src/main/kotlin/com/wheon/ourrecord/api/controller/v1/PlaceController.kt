package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.UpdateCouplePlaceRequest
import com.wheon.ourrecord.api.controller.v1.response.CouplePlaceMapMarkerResponse
import com.wheon.ourrecord.api.controller.v1.response.CouplePlaceRecordResponse
import com.wheon.ourrecord.api.controller.v1.response.PlaceCategoryResponse
import com.wheon.ourrecord.api.controller.v1.response.PlaceSearchResponse
import com.wheon.ourrecord.client.kakao.KakaoMapClient
import com.wheon.ourrecord.domain.place.PlaceService
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController(
    private val placeService: PlaceService,
    private val kakaoMapClient: KakaoMapClient,
) {
    @GetMapping("/v1/places/search")
    fun searchPlace(
        apiUser: ApiUser,
        @RequestParam keyword: String,
    ): ApiResponse<List<PlaceSearchResponse>> {
        val result = kakaoMapClient.searchPlace(keyword)
        return ApiResponse.success(PlaceSearchResponse.of(result.places))
    }

    @GetMapping("/v1/place-categories")
    fun getPlaceCategories(
        apiUser: ApiUser,
    ): ApiResponse<List<PlaceCategoryResponse>> {
        val categories = placeService.getPlaceCategories()
        return ApiResponse.success(PlaceCategoryResponse.of(categories))
    }

    @GetMapping("/v1/couple-places")
    fun getCouplePlaces(
        apiCoupleUser: ApiCoupleUser,
    ): ApiResponse<List<CouplePlaceMapMarkerResponse>> {
        val markers = placeService.getCouplePlaceMapMarkers(apiCoupleUser)
        return ApiResponse.success(CouplePlaceMapMarkerResponse.of(markers))
    }

    @GetMapping("/v1/couple-places/{couplePlaceId}/records")
    fun getCouplePlaceRecords(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable couplePlaceId: Long,
    ): ApiResponse<List<CouplePlaceRecordResponse>> {
        val records = placeService.getCouplePlaceRecords(
            apiCoupleUser = apiCoupleUser,
            couplePlaceId = couplePlaceId,
        )
        return ApiResponse.success(CouplePlaceRecordResponse.of(records))
    }

    @PutMapping("/v1/couple-places/{couplePlaceId}")
    fun updateCouplePlace(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable couplePlaceId: Long,
        @RequestBody request: UpdateCouplePlaceRequest,
    ): ApiResponse<Any> {
        request.validate()
        placeService.updateCouplePlace(
            apiCoupleUser = apiCoupleUser,
            couplePlaceId = couplePlaceId,
            categoryCode = request.categoryCode,
        )
        return ApiResponse.success()
    }

    @DeleteMapping("/v1/couple-places/{couplePlaceId}")
    fun deleteCouplePlace(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable couplePlaceId: Long,
    ): ApiResponse<Any> {
        placeService.deleteCouplePlace(
            apiCoupleUser = apiCoupleUser,
            couplePlaceId = couplePlaceId,
        )
        return ApiResponse.success()
    }
}
