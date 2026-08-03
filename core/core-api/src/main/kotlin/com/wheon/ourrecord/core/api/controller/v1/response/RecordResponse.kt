package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.domain.member.Member
import com.wheon.ourrecord.core.domain.record.Record
import com.wheon.ourrecord.core.domain.record.RecordMedia
import java.time.LocalDate

data class RecordResponse(
    val id: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val media: List<MediaResponse>,
    val authorMember: MemberProfileResponse,
) {
    companion object {
        fun of(
            records: List<Record>,
            mediaMap: Map<Long, List<RecordMedia>>,
            memberMap: Map<Long, Member>,
        ): List<RecordResponse> {
            return records.map {
                RecordResponse(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    visitedOn = it.visitedOn,
                    media = mediaMap[it.id]!!.map { media ->
                        MediaResponse(
                            id = media.id,
                            url = media.url,
                        )
                    },
                    authorMember = MemberProfileResponse(
                        nickname = memberMap[it.memberId]!!.nickname,
                        emoji = memberMap[it.memberId]!!.emoji,
                    ),
                )
            }
        }
    }
}
