package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.AddRecordAtCouplePlaceRequest
import com.wheon.ourrecord.api.controller.v1.request.AddRecordRequest
import com.wheon.ourrecord.api.controller.v1.request.UpdateRecordDetailsRequest
import com.wheon.ourrecord.api.controller.v1.request.UpdateRecordRequest
import com.wheon.ourrecord.api.controller.v1.response.CreateRecordResponse
import com.wheon.ourrecord.api.controller.v1.response.RecordTimelineResponse
import com.wheon.ourrecord.domain.record.RecordService
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RecordController(
    private val recordService: RecordService,
) {
    @PostMapping("/v1/records")
    fun addRecord(
        apiCoupleUser: ApiCoupleUser,
        @RequestBody request: AddRecordRequest,
    ): ApiResponse<CreateRecordResponse> {
        val recordId = recordService.create(
            apiCoupleUser = apiCoupleUser,
            addRecord = request.toAddRecord(),
            imageHandle = request.toImageHandle(),
        )
        return ApiResponse.success(CreateRecordResponse(recordId))
    }

    @PostMapping("/v1/couple-places/{couplePlaceId}/records")
    fun addRecordAtCouplePlace(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable couplePlaceId: Long,
        @RequestBody request: AddRecordAtCouplePlaceRequest,
    ): ApiResponse<CreateRecordResponse> {
        val recordId = recordService.createAtCouplePlace(
            apiCoupleUser = apiCoupleUser,
            couplePlaceId = couplePlaceId,
            newRecord = request.toNewRecord(
                coupleId = apiCoupleUser.coupleId,
                authorMemberId = apiCoupleUser.memberId,
                couplePlaceId = couplePlaceId,
            ),
            imageHandle = request.toImageHandle(),
        )
        return ApiResponse.success(CreateRecordResponse(recordId))
    }

    @GetMapping("/v1/records/timeline")
    fun getRecordTimeline(
        apiCoupleUser: ApiCoupleUser,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) cursor: String?,
    ): ApiResponse<RecordTimelineResponse> {
        val timeline = recordService.getTimeline(
            apiCoupleUser = apiCoupleUser,
            size = size,
            cursor = cursor,
        )
        return ApiResponse.success(RecordTimelineResponse.of(timeline))
    }

    @PutMapping("/v1/records/{recordId}")
    fun updateRecord(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable recordId: Long,
        @RequestBody request: UpdateRecordRequest,
    ): ApiResponse<Any> {
        recordService.update(
            apiCoupleUser = apiCoupleUser,
            recordId = recordId,
            updateRecord = request.toUpdateRecord(),
        )
        return ApiResponse.success()
    }

    @PatchMapping("/v1/records/{recordId}")
    fun updateRecordDetails(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable recordId: Long,
        @RequestBody request: UpdateRecordDetailsRequest,
    ): ApiResponse<Any> {
        recordService.updateDetails(
            apiCoupleUser = apiCoupleUser,
            recordId = recordId,
            updateRecordDetails = request.toUpdateRecordDetails(),
        )
        return ApiResponse.success()
    }

    @DeleteMapping("/v1/records/{recordId}")
    fun deleteRecord(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable recordId: Long,
    ): ApiResponse<Any> {
        recordService.delete(
            apiCoupleUser = apiCoupleUser,
            recordId = recordId,
        )
        return ApiResponse.success()
    }
}
