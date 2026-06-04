package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.CoupleState
import com.wheon.ourrecord.storage.db.core.CoupleEntity
import com.wheon.ourrecord.storage.db.core.CoupleRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoupleManager(
    private val coupleRepository: CoupleRepository,
) {
    @Transactional
    fun create(userId: Long, newCouple: NewCouple): Long {
        val savedCouple = coupleRepository.save(
            CoupleEntity(
                anniversaryDate = newCouple.anniversaryDate,
                ownerId = userId,
                state = CoupleState.CREATED,
            ),
        )
        return savedCouple.id
    }
}
