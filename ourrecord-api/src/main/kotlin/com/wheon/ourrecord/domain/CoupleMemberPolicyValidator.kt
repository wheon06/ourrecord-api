package com.wheon.ourrecord.domain

import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
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
