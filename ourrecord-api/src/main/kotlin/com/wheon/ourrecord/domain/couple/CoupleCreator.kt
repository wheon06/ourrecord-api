package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.storage.db.core.CoupleEntity
import com.wheon.ourrecord.storage.db.core.CoupleRepository
import org.springframework.stereotype.Component

@Component
class CoupleCreator(
    private val coupleRepository: CoupleRepository,
) {
    fun create(ownerUserId: Long, partnerUserId: Long): Long {
        val savedCouple = coupleRepository.save(
            CoupleEntity(
                ownerUserId = ownerUserId,
                partnerUserId = partnerUserId,
            )
        )
        return savedCouple.id
    }
}
