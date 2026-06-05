package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.CreateCoupleInviteRequest
import com.wheon.ourrecord.api.controller.v1.request.CreateMemberProfileRequest
import com.wheon.ourrecord.api.controller.v1.response.CoupleInviteResponse
import com.wheon.ourrecord.api.controller.v1.response.CreateCoupleInviteResponse
import com.wheon.ourrecord.api.controller.v1.response.CreateCoupleResponse
import com.wheon.ourrecord.domain.CoupleInviteService
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoupleInviteController(
    private val coupleInviteService: CoupleInviteService,
) {
    @PostMapping("/v1/couple-invites")
    fun createInvite(
        apiUser: ApiUser,
        @RequestBody request: CreateCoupleInviteRequest,
    ): ApiResponse<CreateCoupleInviteResponse> {
        val inviteKey = coupleInviteService.createInvite(apiUser, request.toNewCoupleInvite())
        return ApiResponse.success(CreateCoupleInviteResponse(inviteKey))
    }

    @GetMapping("/v1/couple-invites/{inviteKey}")
    fun getInviteByInviteKey(
        @PathVariable inviteKey: String,
    ): ApiResponse<CoupleInviteResponse> {
        val coupleInvite = coupleInviteService.getInviteByInvite(inviteKey)
        return ApiResponse.success(CoupleInviteResponse.of(coupleInvite))
    }

    @PostMapping("/v1/couple-invites/{inviteKey}/accept")
    fun acceptInvite(
        apiUser: ApiUser,
        @PathVariable inviteKey: String,
        @RequestBody request: CreateMemberProfileRequest,
    ): ApiResponse<CreateCoupleResponse> {
        val coupleId = coupleInviteService.acceptInvite(apiUser, inviteKey, request.toNewPartnerProfile())
        return ApiResponse.success(CreateCoupleResponse(coupleId))
    }
}
