package com.wheon.ourrecord.core.domain.member

import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberFinder: MemberFinder,
    private val memberManager: MemberManager,
) {
    fun getMember(user: User): Member {
        return memberFinder.find(user.id)
    }

    fun getSpaceMembers(spaceId: Long): List<Member> {
        return memberFinder.findSpaceMembers(spaceId)
    }

    fun updateProfile(user: User, memberProfile: MemberProfile): Long {
        val member = memberFinder.find(user.id)
        return memberManager.updateProfile(member.userId, memberProfile)
    }
}
