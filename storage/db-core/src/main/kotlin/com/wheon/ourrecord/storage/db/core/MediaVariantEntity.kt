package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.MediaVariantState
import com.wheon.ourrecord.core.enums.MediaVariantType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(
    name = "media_variant",
    indexes = [
        Index(
            name = "udx_media_variant_media_asset_id_variant_type",
            columnList = "mediaAssetId, variantType",
            unique = true,
        ),
    ],
)
class MediaVariantEntity(
    val mediaAssetId: Long,
    @Enumerated(EnumType.STRING)
    val state: MediaVariantState,
    @Enumerated(EnumType.STRING)
    val variantType: MediaVariantType,
    val objectKey: String,
    val mimeType: String,
    val byteSize: Long,
    val width: Int,
    val height: Int,
    val failureReason: String?,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.MIN,
) : BaseIdEntity()
