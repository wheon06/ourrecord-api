package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.CoupleInviteState
import com.wheon.ourrecord.core.enums.CoupleMemberRole
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleEntity
import com.wheon.ourrecord.storage.db.core.CoupleInviteEntity
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import com.wheon.ourrecord.storage.db.core.CoupleMemberEntity
import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import com.wheon.ourrecord.storage.db.core.CoupleRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoupleInviteManager(
    private val coupleInviteRepository: CoupleInviteRepository,
    private val coupleInviteKeyGenerator: CoupleInviteKeyGenerator,
    private val coupleRepository: CoupleRepository,
    private val coupleMemberRepository: CoupleMemberRepository,
) {
    fun create(userId: Long, newCoupleInvite: NewCoupleInvite): String {
        val inviteKey = coupleInviteKeyGenerator.generate()
        return coupleInviteRepository.save(
            CoupleInviteEntity(
                inviteKey = inviteKey,
                ownerUserId = userId,
                anniversaryDate = newCoupleInvite.anniversaryDate,
                ownerDisplayName = newCoupleInvite.ownerDisplayName,
                ownerEmoji = newCoupleInvite.ownerEmoji,
                state = CoupleInviteState.CREATED,
            ),
        ).inviteKey
    }

    @Transactional
    fun accept(userId: Long, inviteKey: String, partnerProfile: NewPartnerProfile): Long {
        val coupleInvite = coupleInviteRepository.findByInviteKeyAndStateAndStatusForUpdate(inviteKey, CoupleInviteState.CREATED, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.INVALID_INVITE_KEY)

        val savedCouple = coupleRepository.save(
            CoupleEntity(
                anniversaryDate = coupleInvite.anniversaryDate,
                ownerUserId = coupleInvite.ownerUserId,
                partnerUserId = userId,
            ),
        )

        coupleMemberRepository.saveAll(
            listOf(
                CoupleMemberEntity(
                    coupleId = savedCouple.id,
                    userId = coupleInvite.ownerUserId,
                    displayName = coupleInvite.ownerDisplayName,
                    emoji = coupleInvite.ownerEmoji,
                    role = CoupleMemberRole.OWNER,
                ),
                CoupleMemberEntity(
                    coupleId = savedCouple.id,
                    userId = userId,
                    displayName = partnerProfile.displayName,
                    emoji = partnerProfile.emoji,
                    role = CoupleMemberRole.MEMBER,
                ),
            ),
        )

        coupleInvite.joined(userId)

        return savedCouple.id
    }
}
