package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.record.RecordTimeline
import com.wheon.ourrecord.domain.record.RecordTimelineItem
import java.time.LocalDate
import java.time.LocalDateTime

data class RecordTimelineResponse(
    val items: List<RecordTimelineItemResponse>,
    val nextCursor: String?,
) {
    companion object {
        fun of(timeline: RecordTimeline): RecordTimelineResponse {
            return RecordTimelineResponse(
                items = RecordTimelineItemResponse.of(timeline.items),
                nextCursor = timeline.nextCursor,
            )
        }
    }
}

data class RecordTimelineItemResponse(
    val recordId: Long,
    val imageId: Long,
    val photoUrl: String,
    val thumbnailUrl: String,
    val visitedOn: LocalDate,
    val placeName: String,
    val title: String,
    val authorProfile: RecordAuthorProfileResponse,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun of(items: List<RecordTimelineItem>): List<RecordTimelineItemResponse> {
            return items.map {
                RecordTimelineItemResponse(
                    recordId = it.recordId,
                    imageId = it.imageId,
                    photoUrl = it.photoUrl,
                    thumbnailUrl = it.thumbnailUrl,
                    visitedOn = it.visitedOn,
                    placeName = it.placeName,
                    title = it.title,
                    authorProfile = RecordAuthorProfileResponse.of(it.authorProfile),
                    createdAt = it.createdAt,
                )
            }
        }
    }
}
