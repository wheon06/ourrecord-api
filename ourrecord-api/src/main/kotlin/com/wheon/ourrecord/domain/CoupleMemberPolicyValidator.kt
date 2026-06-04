package com.wheon.ourrecord.domain

import com.wheon.ourrecord.api.support.error.ApiException
import com.wheon.ourrecord.api.support.error.ErrorType
import com.wheon.ourrecord.core.enums.CoupleState
import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import org.springframework.stereotype.Component

@Component
class CoupleMemberPolicyValidator(
    private val coupleMemberRepository: CoupleMemberRepository,
) {
    fun validatorAdd(userId: Long) {
        val hasCouples = coupleMemberRepository.findByUserId(userId).filter { it.isActive() }
        if (hasCouples.isNotEmpty()) {
            throw ApiException(ErrorType.ALREADY_JOINED_COUPLE)
        }
    }
}
