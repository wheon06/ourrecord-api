package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.record.CouplePlaceRecord
import com.wheon.ourrecord.domain.record.RecordAuthorProfile
import com.wheon.ourrecord.domain.record.RecordImage
import com.wheon.ourrecord.domain.record.RecordPlace
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class CouplePlaceRecordResponse(
    val recordId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val authorProfile: RecordAuthorProfileResponse,
    val place: RecordPlaceResponse,
    val images: List<RecordImageResponse>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun of(records: List<CouplePlaceRecord>): List<CouplePlaceRecordResponse> {
            return records.map {
                CouplePlaceRecordResponse(
                    recordId = it.recordId,
                    title = it.title,
                    content = it.content,
                    visitedOn = it.visitedOn,
                    authorProfile = RecordAuthorProfileResponse.of(it.authorProfile),
                    place = RecordPlaceResponse.of(it.place),
                    images = RecordImageResponse.of(it.images),
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                )
            }
        }
    }
}

data class RecordAuthorProfileResponse(
    val id: Long,
    val displayName: String,
    val emoji: String,
) {
    companion object {
        fun of(authorProfile: RecordAuthorProfile): RecordAuthorProfileResponse {
            return RecordAuthorProfileResponse(
                id = authorProfile.id,
                displayName = authorProfile.displayName,
                emoji = authorProfile.emoji,
            )
        }
    }
}

data class RecordPlaceResponse(
    val couplePlaceId: Long,
    val placeId: Long,
    val categoryCode: String?,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
) {
    companion object {
        fun of(place: RecordPlace): RecordPlaceResponse {
            return RecordPlaceResponse(
                couplePlaceId = place.couplePlaceId,
                placeId = place.placeId,
                categoryCode = place.categoryCode,
                name = place.name,
                address = place.address,
                roadAddress = place.roadAddress,
                latitude = place.latitude,
                longitude = place.longitude,
            )
        }
    }
}

data class RecordImageResponse(
    val id: Long,
    val fileUrl: String,
    val thumbnailUrl: String,
    val sortOrder: Int,
    val width: Int,
    val height: Int,
) {
    companion object {
        fun of(images: List<RecordImage>): List<RecordImageResponse> {
            return images.map {
                RecordImageResponse(
                    id = it.imageId,
                    fileUrl = it.fileUrl,
                    thumbnailUrl = it.thumbnailUrl,
                    sortOrder = it.sortOrder,
                    width = it.width,
                    height = it.height,
                )
            }
        }
    }
}
