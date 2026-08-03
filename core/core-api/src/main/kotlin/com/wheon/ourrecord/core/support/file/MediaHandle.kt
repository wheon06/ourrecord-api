package com.wheon.ourrecord.core.support.file

import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType

data class MediaHandle(
    val addMediaUrls: List<String>,
    val deleteMediaUrls: List<String>,
) {
    private val mediaUrlPrefix = StorageServe.CDN + StorageServe.BUCKET

    init {
        addMediaUrls.forEach { validateUrl(it) }
        deleteMediaUrls.forEach { validateUrl(it) }
    }

    fun hasMediaToAdd(): Boolean {
        return addMediaUrls.isNotEmpty()
    }

    fun hasMediaToDelete(): Boolean {
        return deleteMediaUrls.isNotEmpty()
    }

    private fun validateUrl(url: String) {
        if (!url.startsWith(mediaUrlPrefix)) {
            throw CoreException(ErrorType.RECORD_BAD_IMAGE)
        }
    }
}
