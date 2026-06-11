package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.MediaAssetState
import com.wheon.ourrecord.core.enums.StorageProviderType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "media_asset")
class MediaAssetEntity(
    val coupleId: Long,
    val ownerMemberId: Long,
    @Enumerated(EnumType.STRING)
    val state: MediaAssetState,
    @Enumerated(EnumType.STRING)
    val storageProvider: StorageProviderType,
    val bucket: String,
    val objectKey: String,
    val originalFileName: String,
    val mimeType: String,
    val byteSize: Long,
    val width: Int,
    val height: Int,
    val failureReason: String?,
) : BaseEntity()
