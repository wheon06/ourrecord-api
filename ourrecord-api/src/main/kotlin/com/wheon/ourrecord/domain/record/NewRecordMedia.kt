package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType

data class NewRecordMedia(
    val resourceType: ResourceType,
    val url: String,
) {
    companion object {
        private const val MEDIA_URL_PREFIX = "https://14.6.152.212:9000/ourrecord/"
    }

    init {
        if (!url.startsWith(MEDIA_URL_PREFIX)) {
            throw ApiException(ErrorType.RECORD_BAD_IMAGE)
        }

        val resourceName = url.removePrefix(MEDIA_URL_PREFIX).substringBefore("/")
        if (resourceName != resourceType.resourceName) {
            throw ApiException(ErrorType.RECORD_BAD_IMAGE)
        }
    }
}
