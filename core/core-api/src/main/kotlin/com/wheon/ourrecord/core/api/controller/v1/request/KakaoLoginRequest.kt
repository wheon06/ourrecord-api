package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.enums.PlatformType

data class KakaoLoginRequest(
    val accessToken: String,
    val device: com.wheon.ourrecord.core.api.controller.v1.request.DeviceRequest,
)

data class DeviceRequest(
    val installId: String,
    val platform: PlatformType,
    val pushToken: String?,
    val appVersion: String,
)
