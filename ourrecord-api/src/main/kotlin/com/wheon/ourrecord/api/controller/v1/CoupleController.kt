package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.CreateCoupleRequest
import com.wheon.ourrecord.api.controller.v1.request.CreateMemberProfileRequest
import com.wheon.ourrecord.api.controller.v1.response.CoupleInviteResponse
import com.wheon.ourrecord.api.controller.v1.response.CoupleResponse
import com.wheon.ourrecord.api.controller.v1.response.CreateCoupleResponse
import com.wheon.ourrecord.api.support.ApiUser
import com.wheon.ourrecord.api.support.response.ApiResponse
import com.wheon.ourrecord.domain.CoupleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoupleController(
    private val coupleService: CoupleService,
) {
    @PostMapping("/v1/couples")
    fun create(
        apiUser: ApiUser,
        @RequestBody request: CreateCoupleRequest,
    ): ApiResponse<CreateCoupleResponse> {
        val coupleId = coupleService.create(apiUser, request.toNewCouple())
        return ApiResponse.success(CreateCoupleResponse(coupleId))
    }

    @PostMapping("/v1/couples/{coupleId}/invite")
    fun createInvite(
        apiUser: ApiUser,
        @PathVariable coupleId: Long,
    ): ApiResponse<CoupleInviteResponse> {
        val inviteKey = coupleService.createInvite(apiUser, coupleId)
        return ApiResponse.success(CoupleInviteResponse(inviteKey))
    }

    @GetMapping("/v1/couple-invites/{inviteKey}")
    fun getCoupleByInviteKey(
        @PathVariable inviteKey: String,
    ): ApiResponse<CoupleResponse> {
        val couple = coupleService.getCoupleByInvite(inviteKey)
        return ApiResponse.success(CoupleResponse.of(couple))
    }
}
