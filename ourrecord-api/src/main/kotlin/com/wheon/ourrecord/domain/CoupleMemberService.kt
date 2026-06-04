package com.wheon.ourrecord.domain

import com.wheon.ourrecord.api.support.ApiUser
import org.springframework.stereotype.Service

@Service
class CoupleMemberService(
    private val coupleMemberManager: CoupleMemberManager,
    private val coupleMemberPolicyValidator: CoupleMemberPolicyValidator,
) {
    fun add(apiUser: ApiUser, coupleId: Long, newMemberProfile: NewMemberProfile): Long {
        coupleMemberPolicyValidator.validatorAdd(apiUser.id)
        return coupleMemberManager.add(apiUser.id, coupleId, newMemberProfile)
    }
}
