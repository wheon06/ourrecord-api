package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType

data class NewRecordMedia(
    val resourceType: ResourceType,
    val url: String,
) {
    companion object {
        private const val MEDIA_URL_PREFIX = "https://14.6.152.212:9000/ourrecord/"
    }

    init {
        if (!url.startsWith(MEDIA_URL_PREFIX)) {
            throw CoreException(ErrorType.RECORD_BAD_IMAGE)
        }

        val resourceName = url.removePrefix(MEDIA_URL_PREFIX).substringBefore("/")
        if (resourceName != resourceType.resourceName) {
            throw CoreException(ErrorType.RECORD_BAD_IMAGE)
        }
    }
}
