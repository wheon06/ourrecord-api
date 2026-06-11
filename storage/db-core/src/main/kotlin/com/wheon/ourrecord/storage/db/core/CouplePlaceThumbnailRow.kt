package com.wheon.ourrecord.storage.db.core

interface CouplePlaceThumbnailRow {
    fun getCouplePlaceId(): Long
    fun getBucket(): String
    fun getObjectKey(): String
}
