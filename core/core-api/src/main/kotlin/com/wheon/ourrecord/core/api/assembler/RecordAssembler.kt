package com.wheon.ourrecord.core.api.assembler

import com.wheon.ourrecord.core.domain.member.MemberService
import com.wheon.ourrecord.core.domain.record.NewRecordMedia
import com.wheon.ourrecord.core.domain.record.RecordContent
import com.wheon.ourrecord.core.domain.record.RecordService
import com.wheon.ourrecord.core.domain.record.RecordTarget
import com.wheon.ourrecord.core.domain.user.User
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
}
