package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.domain.member.MemberFinder
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.SpaceRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Component
class SpaceManager(
    private val spaceRepository: SpaceRepository,
    private val memberFinder: MemberFinder,
) {
    @Transactional
    fun applyAnniversaryDate(userId: Long, date: LocalDate) {
        val member = memberFinder.find(userId)
        spaceRepository.findByIdAndStatus(member.spaceId, EntityStatus.ACTIVE)?.applyAnniversaryDate(date)
    }
}
