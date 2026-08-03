package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.domain.record.RecordContent
import com.wheon.ourrecord.core.support.file.MediaHandle
import java.time.LocalDate

data class ModifyRecordRequest(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val addMediaUrls: List<String>,
    val deleteMediaUrls: List<String>,
) {
    fun toContent(): RecordContent {
        return RecordContent(
            title = title,
            content = content,
            visitedOn = visitedOn,
        )
    }

    fun toMediaHandle(): MediaHandle {
        return MediaHandle(
            addMediaUrls = addMediaUrls,
            deleteMediaUrls = deleteMediaUrls,
        )
    }
}
