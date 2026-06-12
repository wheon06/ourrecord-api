package com.wheon.ourrecord.domain.couple

sealed interface UserRelationship {
    data object None : UserRelationship

    data class WaitingInvite(
        val invite: CoupleInvite,
    ) : UserRelationship

    data class JoinedCouple(
        val couple: Couple,
    ) : UserRelationship
}
