package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.domain.member.MemberProfileGenerator
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.SpaceInviteState
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.MemberEntity
import com.wheon.ourrecord.storage.db.core.MemberRepository
import com.wheon.ourrecord.storage.db.core.SpaceEntity
import com.wheon.ourrecord.storage.db.core.SpaceInviteEntity
import com.wheon.ourrecord.storage.db.core.SpaceInviteRepository
import com.wheon.ourrecord.storage.db.core.SpaceRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class SpaceInviteManager(
    private val spaceRepository: SpaceRepository,
    private val spaceInviteRepository: SpaceInviteRepository,
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun create(userId: Long): String {
        val savedSpace = spaceRepository.save(
            SpaceEntity(
                userId = userId,
                anniversaryDate = null,
            ),
        )
        val profile = MemberProfileGenerator.generate()
        memberRepository.save(
            MemberEntity(
                userId = userId,
                spaceId = savedSpace.id,
                nickname = profile.nickname,
                emoji = profile.emoji,
            ),
        )

        val savedInvite = spaceInviteRepository.save(
            SpaceInviteEntity(
                userId = userId,
                inviteKey = UUID.randomUUID().toString().replace("-", ""),
                spaceId = savedSpace.id,
                state = SpaceInviteState.PENDING,
            ),
        )

        return savedInvite.inviteKey
    }

    @Transactional
    fun accept(userId: Long, inviteKey: String) {
        val invite = spaceInviteRepository.findByInviteKey(inviteKey) ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        if (invite.state != SpaceInviteState.PENDING) throw CoreException(ErrorType.INVITE_STATE_INVALID)
        if (invite.userId == userId) throw CoreException(ErrorType.INVALID_INVITE_KEY)
        spaceRepository.findByIdAndStatus(invite.spaceId, EntityStatus.ACTIVE) ?: throw CoreException(ErrorType.NOT_FOUND_DATA)

        invite.accepted()

        // 수락한 사용자 소유의 기존 공간은 삭제 처리
        spaceInviteRepository.findByUserId(userId)?.let {
            it.expired()
            spaceRepository.findByIdAndStatus(it.spaceId, EntityStatus.ACTIVE)?.delete()
            memberRepository.findByUserIdAndStatus(userId, EntityStatus.ACTIVE)?.delete()
        }

        val profile = MemberProfileGenerator.generate()
        memberRepository.save(
            MemberEntity(
                userId = userId,
                spaceId = invite.spaceId,
                nickname = profile.nickname,
                emoji = profile.emoji,
            ),
        )
    }
}
