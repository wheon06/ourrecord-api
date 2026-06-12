package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.assembler.UserAssembler
import com.wheon.ourrecord.api.controller.v1.request.UpdateDeviceRequest
import com.wheon.ourrecord.api.controller.v1.response.UserMeResponse
import com.wheon.ourrecord.api.controller.v1.response.UserMyResponse
import com.wheon.ourrecord.domain.UserService
import com.wheon.ourrecord.domain.couple.CoupleService
import com.wheon.ourrecord.domain.couple.UserCouple
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userAssembler: UserAssembler,
    private val userService: UserService,
    private val coupleService: CoupleService,
) {
    @PutMapping("/v1/devices/current")
    fun updateDevice(
        apiUser: ApiUser,
        @RequestBody request: UpdateDeviceRequest,
    ): ApiResponse<Any> {
        userService.updateCurrentDevice(
            apiUser = apiUser,
            pushToken = request.pushToken,
        )
        return ApiResponse.success()
    }

    @GetMapping("/v1/users/me")
    fun getUserMe(
        apiUser: ApiUser,
    ): ApiResponse<UserMeResponse> {
        val userCouple = coupleService.findUserCouple(apiUser)
        return ApiResponse.success(
            UserMeResponse(
                userId = apiUser.id,
                isCoupleMember = when (userCouple) {
                    UserCouple.None -> false
                    is UserCouple.Joined -> true
                },
            ),
        )
    }

    @GetMapping("/v1/users/my")
    fun getUserMy(
        apiUser: ApiUser,
    ): ApiResponse<UserMyResponse> {
        return ApiResponse.success(
            userAssembler.getUserMy(apiUser),
        )
    }
}
