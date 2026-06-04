package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.CreateMemberProfileRequest
import com.wheon.ourrecord.domain.CoupleMemberService
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoupleMemberController(
    private val coupleMemberService: CoupleMemberService,
) {
    @PostMapping("/v1/couples/{coupleId}/members")
    fun addCoupleMember(
        apiUser: ApiUser,
        @PathVariable coupleId: Long,
        @RequestBody request: CreateMemberProfileRequest,
    ): ApiResponse<Any> {
        coupleMemberService.add(apiUser, coupleId, request.toNewMemberProfile())
        return ApiResponse.success()
    }
}
