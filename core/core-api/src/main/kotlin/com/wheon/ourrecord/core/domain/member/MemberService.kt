package com.wheon.ourrecord.core.domain.member

import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberFinder: MemberFinder,
) {
    fun getMember(user: User): Member {
        return memberFinder.find(user.id)
    }
}
