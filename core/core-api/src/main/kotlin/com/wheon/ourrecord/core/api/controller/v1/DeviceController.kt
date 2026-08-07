package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.api.controller.v1.request.PutDeviceRequest
import com.wheon.ourrecord.core.domain.DeviceService
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DeviceController(
    private val deviceService: DeviceService
) {
    @PutMapping("/api/v1/devices/me")
    fun putDevice(
        user: User,
        @RequestBody request: PutDeviceRequest,
    ): ApiResponse<Any> {
        deviceService.loginDevice(user, request.pushKey, request.platform)
        return ApiResponse.success()
    }

    @DeleteMapping("/api/v1/devices")
    fun deleteDevice(
        @RequestParam pushKey: String,
    ): ApiResponse<Any> {
        deviceService.deleteDevice(pushKey)
        return ApiResponse.success()
    }
}
