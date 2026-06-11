package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.CreateNotificationRequest
import com.wheon.ourrecord.api.controller.v1.response.CreateNotificationResponse
import com.wheon.ourrecord.api.controller.v1.response.NotificationResponse
import com.wheon.ourrecord.domain.notification.NotificationService
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationController(
    private val notificationService: NotificationService,
) {
    @PostMapping("/v1/notifications")
    fun createNotification(
        apiCoupleUser: ApiCoupleUser,
        @RequestBody request: CreateNotificationRequest,
    ): ApiResponse<CreateNotificationResponse> {
        val notificationId = notificationService.create(
            apiCoupleUser = apiCoupleUser,
            toUserId = request.toUserId,
            type = request.type,
            metadata = request.metadata,
        )
        return ApiResponse.success(CreateNotificationResponse(notificationId))
    }

    @GetMapping("/v1/notifications")
    fun getNotifications(
        apiCoupleUser: ApiCoupleUser,
    ): ApiResponse<List<NotificationResponse>> {
        return ApiResponse.success(
            NotificationResponse.of(notificationService.getNotifications(apiCoupleUser)),
        )
    }

    @PatchMapping("/v1/notifications/{notificationId}/read")
    fun markAsRead(
        apiCoupleUser: ApiCoupleUser,
        @PathVariable notificationId: Long,
    ): ApiResponse<Any> {
        notificationService.markAsRead(
            apiCoupleUser = apiCoupleUser,
            notificationId = notificationId,
        )
        return ApiResponse.success()
    }

    @PatchMapping("/v1/notifications/read-all")
    fun markAllAsRead(
        apiCoupleUser: ApiCoupleUser,
    ): ApiResponse<Any> {
        notificationService.markAllAsRead(apiCoupleUser)
        return ApiResponse.success()
    }
}
