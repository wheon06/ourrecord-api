package com.wheon.ourrecord.storage.db.core

data class RecordImageRow(
    val recordId: Long,
    val mediaAssetId: Long,
    val sortOrder: Int,
    val bucket: String,
    val objectKey: String,
    val width: Int,
    val height: Int,
)
