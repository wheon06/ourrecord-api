package com.wheon.ourrecord.core.api.assembler

import com.wheon.ourrecord.core.domain.member.MemberService
import com.wheon.ourrecord.core.domain.place.NewPlace
import com.wheon.ourrecord.core.domain.place.PlaceService
import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Component

@Component
class PlaceAssembler(
    private val memberService: MemberService,
    private val placeService: PlaceService,
) {
    fun addPlace(user: User, newPlace: NewPlace): Long {
        val member = memberService.getMember(user)
        return placeService.addPlace(user, member.spaceId, newPlace)
    }
}
