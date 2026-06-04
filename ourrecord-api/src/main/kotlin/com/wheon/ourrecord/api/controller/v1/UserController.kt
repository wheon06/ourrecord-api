package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.UpdateDeviceRequest
import com.wheon.ourrecord.support.ApiUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @PutMapping("/v1/devices/current")
    fun updateDevice(
        apiUser: ApiUser,
        @RequestBody request: UpdateDeviceRequest,
    ): ApiResponse<Any> {
        // TODO
        return ApiResponse.success()
    }
}
