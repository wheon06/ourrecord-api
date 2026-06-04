package com.wheon.ourrecord.domain

import com.wheon.ourrecord.storage.db.core.CoupleInviteEntity
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import org.springframework.stereotype.Component

@Component
class CoupleInviteManager(
    private val coupleInviteRepository: CoupleInviteRepository,
    private val coupleInviteKeyGenerator: CoupleInviteKeyGenerator,
) {
    fun create(coupleId: Long): String {
        val inviteKey = coupleInviteKeyGenerator.generate()
        return coupleInviteRepository.save(
            CoupleInviteEntity(
                coupleId = coupleId,
                inviteKey = inviteKey,
            ),
        ).inviteKey
    }
}
