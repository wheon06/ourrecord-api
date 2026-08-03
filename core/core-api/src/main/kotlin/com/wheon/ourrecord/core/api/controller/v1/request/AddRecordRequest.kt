package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.domain.record.RecordContent
import com.wheon.ourrecord.core.domain.record.RecordTarget
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.core.support.file.MediaHandle
import java.time.LocalDate

data class AddRecordRequest(
    val placeId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val media: List<String>,
) {
    fun toTarget(): RecordTarget {
        return RecordTarget(placeId)
    }

    fun toContent(): RecordContent {
        if (title.isBlank()) throw CoreException(ErrorType.INVALID_REQUEST)
        if (content.isBlank()) throw CoreException(ErrorType.INVALID_REQUEST)

        return RecordContent(
            title = title,
            content = content,
            visitedOn = visitedOn,
        )
    }

    fun toMediaHandle(): MediaHandle {
        return MediaHandle(
            addMediaUrls = media,
            deleteMediaUrls = emptyList(),
        )
    }
}
