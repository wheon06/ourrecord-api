package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.api.assembler.SpaceAssembler
import com.wheon.ourrecord.core.api.controller.v1.response.InviteCheckoutResponse
import com.wheon.ourrecord.core.api.controller.v1.response.InviteResponse
import com.wheon.ourrecord.core.api.controller.v1.response.SpaceMeResponse
import com.wheon.ourrecord.core.domain.space.SpaceService
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class SpaceController(
    private val spaceAssembler: SpaceAssembler,
    private val spaceService: SpaceService,
) {
    @GetMapping("/api/v1/spaces/me")
    fun getMeInfo(user: User): ApiResponse<SpaceMeResponse> {
        return ApiResponse.success(
            spaceAssembler.getMe(user),
        )
    }

    @PostMapping("/api/v1/space-invites")
    fun createInvite(
        user: User,
    ): ApiResponse<String> {
        val inviteKey = spaceService.createInvite(user)
        return ApiResponse.success(inviteKey)
    }

    @GetMapping("/api/v1/space-invites/{inviteKey}")
    fun getInvite(
        @PathVariable inviteKey: String,
    ): ApiResponse<InviteCheckoutResponse> {
        val response = spaceAssembler.getInvite(inviteKey)
        return ApiResponse.success(response)
    }

    @PostMapping("/api/v1/space-invites/{inviteKey}/accept")
    fun acceptInvite(
        user: User,
        @PathVariable inviteKey: String,
    ): ApiResponse<Any> {
        spaceService.acceptInvite(user, inviteKey)
        return ApiResponse.success()
    }

    @GetMapping("/api/v1/space-invites/my")
    fun getInvite(
        user: User,
    ): ApiResponse<InviteResponse> {
        val invite = spaceService.getMyInvite(user)
        return ApiResponse.success(
            InviteResponse(invite.inviteKey, invite.state),
        )
    }

    @PutMapping("/api/v1/spaces/anniversaryDate")
    fun updateAnniversaryDate(
        user: User,
        @RequestBody date: LocalDate,
    ): ApiResponse<Any> {
        spaceService.applyAnniversaryDate(user, date)
        return ApiResponse.success()
    }
}
