package com.wheon.ourrecord.api.controller.v1.request

import com.wheon.ourrecord.core.enums.PlaceSource
import com.wheon.ourrecord.domain.place.AddPlace
import com.wheon.ourrecord.domain.record.AddRecord
import com.wheon.ourrecord.domain.record.NewRecord
import com.wheon.ourrecord.domain.record.RecordImagePolicy
import com.wheon.ourrecord.domain.record.UpdateRecord
import com.wheon.ourrecord.domain.record.UpdateRecordDetails
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import com.wheon.ourrecord.support.file.ImageHandle
import java.math.BigDecimal
import java.time.LocalDate

data class AddRecordRequest(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val place: AddRecordPlaceRequest,
    val images: List<Long>?,
) {
    fun toAddRecord(): AddRecord {
        if (title.isBlank()) throw ApiException(ErrorType.INVALID_REQUEST)
        if (content.isBlank()) throw ApiException(ErrorType.INVALID_REQUEST)

        return AddRecord(
            visitedOn = visitedOn,
            title = title,
            content = content,
            place = place.toAddPlace(),
        )
    }

    fun toImageHandle(): ImageHandle {
        val imageIds = images ?: emptyList()
        validateRecordImageIds(imageIds)
        return ImageHandle(
            addImageIds = imageIds,
            deleteImageIds = emptyList(),
        )
    }
}

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
        if (name.isBlank()) throw ApiException(ErrorType.INVALID_REQUEST)
        if (address.isBlank()) throw ApiException(ErrorType.INVALID_REQUEST)
        if (externalPlaceId.isBlank()) throw ApiException(ErrorType.INVALID_REQUEST)

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
    val place: AddRecordPlaceRequest,
    val images: List<Long>?,
) {
    fun toUpdateRecord(): UpdateRecord {
        if (title.isBlank()) throw ApiException(ErrorType.INVALID_REQUEST)
        if (content.isBlank()) throw ApiException(ErrorType.INVALID_REQUEST)

        val imageIds = images ?: emptyList()
        validateRecordImageIds(imageIds)

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
        validateRecordImageIds(imageIds)
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
        throw ApiException(ErrorType.INVALID_REQUEST)
    }
    if (imageIds.size > RecordImagePolicy.MAX_IMAGE_COUNT) {
        throw ApiException(ErrorType.INVALID_REQUEST)
    }
}
