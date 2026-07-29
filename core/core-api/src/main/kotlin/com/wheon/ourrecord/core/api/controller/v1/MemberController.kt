package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.api.controller.v1.response.MemberProfileResponse
import com.wheon.ourrecord.core.domain.member.MemberService
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService,
) {
    @GetMapping("/api/v1/member-profiles/my")
    fun getMyMemberProfiles(user: User): ApiResponse<MemberProfileResponse> {
        val member = memberService.getMember(user)
        return ApiResponse.success(
            MemberProfileResponse.of(member),
        )
    }
}
