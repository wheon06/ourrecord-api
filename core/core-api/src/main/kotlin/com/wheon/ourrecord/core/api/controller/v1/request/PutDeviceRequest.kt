package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.enums.PlatformType

data class PutDeviceRequest(
    val pushKey: String,
    val platform: PlatformType,
)
