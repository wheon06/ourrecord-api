package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.domain.place.AddPlace
import com.wheon.ourrecord.core.domain.record.NewRecord
import com.wheon.ourrecord.core.domain.record.NewRecordMedia
import com.wheon.ourrecord.core.domain.record.RecordContent
import com.wheon.ourrecord.core.domain.record.RecordImagePolicy
import com.wheon.ourrecord.core.domain.record.RecordTarget
import com.wheon.ourrecord.core.domain.record.UpdateRecord
import com.wheon.ourrecord.core.domain.record.UpdateRecordDetails
import com.wheon.ourrecord.core.enums.PlaceSource
import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.core.support.file.ImageHandle
import java.math.BigDecimal
import java.time.LocalDate

data class AddRecordRequest(
    val placeId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val media: List<com.wheon.ourrecord.core.api.controller.v1.request.AddMediaRequest>,
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

    fun toMedia(): List<NewRecordMedia> {
        return media.map { NewRecordMedia(ResourceType.RECORD, it.url) }
    }
}

data class AddMediaRequest(
    val url: String,
)

data class AddRecordPlaceRequest(
    val categoryCode: String?,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val source: PlaceSource,
    val externalPlaceId: String,
) {
    fun toAddPlace(): AddPlace {
        if (name.isBlank()) throw CoreException(ErrorType.INVALID_REQUEST)
        if (address.isBlank()) throw CoreException(ErrorType.INVALID_REQUEST)
        if (externalPlaceId.isBlank()) throw CoreException(ErrorType.INVALID_REQUEST)

        return AddPlace(
            source = source,
            externalPlaceId = externalPlaceId,
            categoryCode = categoryCode,
            name = name,
            address = address,
            roadAddress = roadAddress,
            latitude = latitude,
            longitude = longitude,
        )
    }
}

data class UpdateRecordRequest(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val place: com.wheon.ourrecord.core.api.controller.v1.request.AddRecordPlaceRequest,
    val images: List<Long>?,
) {
    fun toUpdateRecord(): UpdateRecord {
        if (title.isBlank()) throw CoreException(ErrorType.INVALID_REQUEST)
        if (content.isBlank()) throw CoreException(ErrorType.INVALID_REQUEST)

        val imageIds = images ?: emptyList()
        com.wheon.ourrecord.core.api.controller.v1.request.validateRecordImageIds(imageIds)

        return UpdateRecord(
            visitedOn = visitedOn,
            title = title,
            content = content,
            place = place.toAddPlace(),
            imageIds = imageIds,
        )
    }
}

data class AddRecordAtCouplePlaceRequest(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val images: List<Long>?,
) {
    fun toNewRecord(coupleId: Long, authorMemberId: Long, couplePlaceId: Long): NewRecord {
        return NewRecord(
            coupleId = coupleId,
            authorMemberId = authorMemberId,
            couplePlaceId = couplePlaceId,
            title = title,
            content = content,
            visitedOn = visitedOn,
        )
    }

    fun toImageHandle(): ImageHandle {
        val imageIds = images ?: emptyList()
        com.wheon.ourrecord.core.api.controller.v1.request.validateRecordImageIds(imageIds)
        return ImageHandle(
            addImageIds = imageIds,
            deleteImageIds = emptyList(),
        )
    }
}

data class UpdateRecordDetailsRequest(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
) {
    fun toUpdateRecordDetails(): UpdateRecordDetails {
        return UpdateRecordDetails(
            title = title,
            content = content,
            visitedOn = visitedOn,
        )
    }
}

private fun validateRecordImageIds(imageIds: List<Long>) {
    if (imageIds.size < RecordImagePolicy.MIN_IMAGE_COUNT) {
        throw CoreException(ErrorType.INVALID_REQUEST)
    }
    if (imageIds.size > RecordImagePolicy.MAX_IMAGE_COUNT) {
        throw CoreException(ErrorType.INVALID_REQUEST)
    }
}
