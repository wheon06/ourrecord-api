package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.couple.Couple
import com.wheon.ourrecord.domain.couple.CoupleMember
import com.wheon.ourrecord.domain.couple.UserRelationship
import java.time.LocalDate

data class UserMyResponse(
    val userId: Long,
    val relationship: RelationshipResponse,
) {
    companion object {
        fun of(
            userId: Long,
            relationship: UserRelationship,
        ): UserMyResponse {
            return UserMyResponse(
                userId = userId,
                relationship = when (relationship) {
                    UserRelationship.None -> RelationshipResponse.None
                    is UserRelationship.WaitingInvite -> RelationshipResponse.WaitingInvite(
                        inviteKey = relationship.invite.inviteKey,
                        displayName = relationship.invite.ownerDisplayName,
                        emoji = relationship.invite.ownerEmoji,
                    )
                    is UserRelationship.JoinedCouple -> RelationshipResponse.JoinedCouple(
                        couple = CoupleResponse.of(relationship.couple),
                    )
                },
            )
        }
    }

    sealed interface RelationshipResponse {
        val type: String

        data object None : RelationshipResponse {
            override val type = "NONE"
        }

        data class WaitingInvite(
            val inviteKey: String,
            val displayName: String,
            val emoji: String,
        ) : RelationshipResponse {
            override val type = "WAITING_INVITE"
        }

        data class JoinedCouple(
            val couple: CoupleResponse,
        ) : RelationshipResponse {
            override val type = "JOINED_COUPLE"
        }
    }

    data class CoupleResponse(
        val coupleId: Long,
        val anniversaryDate: LocalDate,
        val ownerProfile: CoupleMemberResponse,
        val partnerProfile: CoupleMemberResponse,
    ) {
        companion object {
            fun of(couple: Couple): CoupleResponse {
                return CoupleResponse(
                    coupleId = couple.id,
                    anniversaryDate = couple.anniversaryDate,
                    ownerProfile = CoupleMemberResponse.of(couple.ownerUserMember),
                    partnerProfile = CoupleMemberResponse.of(couple.partnerUserMember),
                )
            }
        }

        data class CoupleMemberResponse(
            val id: Long,
            val displayName: String,
            val emoji: String,
        ) {
            companion object {
                fun of(member: CoupleMember): CoupleMemberResponse {
                    return CoupleMemberResponse(
                        id = member.userId,
                        displayName = member.displayName,
                        emoji = member.emoji,
                    )
                }
            }
        }
    }
}
