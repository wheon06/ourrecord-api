package com.wheon.ourrecord.domain.couple

sealed interface UserCouple {
    data object None : UserCouple

    data class Joined(
        val memberId: Long,
        val couple: Couple,
    ) : UserCouple
}
