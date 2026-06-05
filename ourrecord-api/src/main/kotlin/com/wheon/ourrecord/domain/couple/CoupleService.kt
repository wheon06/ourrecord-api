package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Service

@Service
class CoupleService(
    private val coupleFinder: CoupleFinder,
) {
    fun findUserCouple(apiUser: ApiUser): UserCouple {
        return coupleFinder.findUserCouple(apiUser.id)
    }
}
