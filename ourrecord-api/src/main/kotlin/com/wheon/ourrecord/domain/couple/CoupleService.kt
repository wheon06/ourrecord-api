package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.domain.user.User
import org.springframework.stereotype.Service

@Service
class CoupleService(
    private val coupleHandler: CoupleHandler,
) {
    fun accept(user: User, inviteKey: String) {
        coupleHandler.accept(user.id, inviteKey)
    }
}
