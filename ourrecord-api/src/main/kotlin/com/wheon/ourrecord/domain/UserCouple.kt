package com.wheon.ourrecord.domain

sealed interface UserCouple {
    data object None : UserCouple

    data class Joined(
        val couple: Couple,
    ) : UserCouple
}
