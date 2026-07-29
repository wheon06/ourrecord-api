package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.core.support.file.StorageServe

data class NewRecordMedia(
    val resourceType: ResourceType,
    val url: String,
) {
    private val mediaUrlPrefix = StorageServe.CDN + StorageServe.BUCKET

    init {
        if (!url.startsWith(mediaUrlPrefix)) {
            throw CoreException(ErrorType.RECORD_BAD_IMAGE)
        }

        val resourceName = url.removePrefix(mediaUrlPrefix).split("/")[1]
        if (resourceName != resourceType.resourceName) {
            throw CoreException(ErrorType.RECORD_BAD_IMAGE)
        }
    }
}
