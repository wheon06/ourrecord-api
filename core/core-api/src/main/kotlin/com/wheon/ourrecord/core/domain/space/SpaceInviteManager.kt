package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.domain.member.MemberProfile
import com.wheon.ourrecord.core.enums.SpaceInviteState
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
    fun create(userId: Long, profile: MemberProfile): String {
        val savedSpace = spaceRepository.save(
            SpaceEntity(
                userId = userId,
            ),
        )
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
}
