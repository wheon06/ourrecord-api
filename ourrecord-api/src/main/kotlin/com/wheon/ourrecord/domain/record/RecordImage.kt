package com.wheon.ourrecord.domain.record

data class RecordImage(
    val imageId: Long,
    val fileUrl: String,
    val thumbnailUrl: String,
    val sortOrder: Int,
    val width: Int,
    val height: Int,
)
