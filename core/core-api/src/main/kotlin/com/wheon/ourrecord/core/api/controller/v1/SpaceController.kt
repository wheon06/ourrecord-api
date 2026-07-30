package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.api.assembler.SpaceAssembler
import com.wheon.ourrecord.core.api.controller.v1.request.CreateSpaceInviteRequest
import com.wheon.ourrecord.core.api.controller.v1.response.MeResponse
import com.wheon.ourrecord.core.domain.space.SpaceService
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SpaceController(
    private val spaceAssembler: SpaceAssembler,
    private val spaceService: SpaceService,
) {
    @GetMapping("/api/v1/spaces/me")
    fun getMeInfo(user: User): ApiResponse<MeResponse> {
        return ApiResponse.success(
            spaceAssembler.getMe(user),
        )
    }

    @PostMapping("/api/v1/space-invites")
    fun createInvite(
        user: User,
        @RequestBody request: CreateSpaceInviteRequest,
    ): ApiResponse<String> {
        val inviteKey = spaceService.createInvite(user, request.toMemberProfile())
        return ApiResponse.success(inviteKey)
    }

    @PostMapping("/api/v1/space-invites/{inviteKey}")
    fun acceptInvite(
        user: User,
        @PathVariable inviteKey: String,
    ): ApiResponse<Any> {
        spaceService.acceptInvite(user, inviteKey)
        return ApiResponse.success()
    }
}
