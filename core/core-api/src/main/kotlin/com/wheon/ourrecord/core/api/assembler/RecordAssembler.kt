package com.wheon.ourrecord.core.api.assembler

import com.wheon.ourrecord.core.api.controller.v1.response.RecordResponse
import com.wheon.ourrecord.core.domain.member.MemberService
import com.wheon.ourrecord.core.domain.record.NewRecordMedia
import com.wheon.ourrecord.core.domain.record.RecordContent
import com.wheon.ourrecord.core.domain.record.RecordService
import com.wheon.ourrecord.core.domain.record.RecordTarget
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.response.PageResponse
import org.springframework.stereotype.Component

@Component
class RecordAssembler(
    private val recordService: RecordService,
    private val memberService: MemberService,
) {
    fun addRecord(user: User, target: RecordTarget, content: RecordContent, media: List<NewRecordMedia>): Long {
        val member = memberService.getMember(user)
        return recordService.create(
            user = user,
            spaceId = member.spaceId,
            target = target,
            content = content,
            media = media,
        )
    }

    fun getRecords(user: User, placeId: Long, offsetLimit: OffsetLimit): PageResponse<RecordResponse> {
        val member = memberService.getMember(user)
        val paging = recordService.getRecords(member.spaceId, placeId, offsetLimit)
        val mediaMap = recordService.findRecordMedia(paging.results)
        val memberMap = memberService.getSpaceMembers(member.spaceId).associateBy { it.id }
        return PageResponse(RecordResponse.of(paging.results, mediaMap, memberMap), paging.hasNext)
    }
}
