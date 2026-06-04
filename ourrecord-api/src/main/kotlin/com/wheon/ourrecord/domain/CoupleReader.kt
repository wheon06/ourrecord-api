package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.CoupleState
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class CoupleReader(
    private val coupleRepository: CoupleRepository,
) {
    fun getCouple(userId: Long, coupleId: Long, state: CoupleState): Couple {
        val couple = coupleRepository.findByIdAndStateAndStatus(coupleId, state, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
        if (couple.ownerId != userId) throw ApiException(ErrorType.NOT_FOUND_DATA)

        return Couple(
            id = couple.id,
            state = couple.state,
            anniversaryDate = couple.anniversaryDate,
            ownerId = couple.ownerId,
        )
    }
}
