package com.wheon.ourrecord.domain.couple

sealed interface UserPendingInvite {
    data object None : UserPendingInvite

    data class Waiting(
        val invite: CoupleInvite,
    ) : UserPendingInvite
}
