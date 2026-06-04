package com.wheon.ourrecord.api.controller.v1.request

import com.wheon.ourrecord.core.enums.PlatformType

data class KakaoLoginRequest(
    val code: String,
    val device: DeviceRequest,
)

data class DeviceRequest(
    val installId: String,
    val platform: PlatformType,
    val pushToken: String?,
    val appVersion: String,
)
