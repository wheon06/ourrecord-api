package com.wheon.ourrecord.core.api.assembler

import com.wheon.ourrecord.core.api.controller.v1.response.PlaceResponse
import com.wheon.ourrecord.core.domain.member.MemberService
import com.wheon.ourrecord.core.domain.place.NewPlace
import com.wheon.ourrecord.core.domain.place.PlaceService
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.response.PageResponse
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

    fun getPlaces(user: User, offsetLimit: OffsetLimit): PageResponse<PlaceResponse> {
        val member = memberService.getMember(user)
        val paging = placeService.getPlaces(member.spaceId, offsetLimit)
        val metaMap = placeService.readMetaMap(paging.results)
        return PageResponse(PlaceResponse.of(paging.results, metaMap), paging.hasNext)
    }
}
