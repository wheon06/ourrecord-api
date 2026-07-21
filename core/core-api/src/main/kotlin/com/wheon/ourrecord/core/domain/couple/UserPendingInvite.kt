package com.wheon.ourrecord.core.domain.couple

sealed interface UserPendingInvite {
    data object None : UserPendingInvite

    data class Waiting(
        val invite: CoupleInvite,
    ) : UserPendingInvite
}
