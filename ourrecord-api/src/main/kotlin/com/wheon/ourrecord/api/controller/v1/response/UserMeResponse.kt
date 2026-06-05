package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.couple.Couple
import com.wheon.ourrecord.domain.couple.CoupleInvite
import com.wheon.ourrecord.domain.couple.CoupleMember
import com.wheon.ourrecord.domain.couple.UserCouple
import java.time.LocalDate

data class UserMeResponse(
    val userId: Long,
    val displayName: String,
    val emoji: String,
    val couple: CoupleResponse?,
    val pendingInvite: CreateCoupleInviteResponse,
) {
    companion object {
        fun of(
            userId: Long,
            userCouple: UserCouple,
            coupleInvite: CoupleInvite,
        ): UserMeResponse {
            return UserMeResponse(
                userId = userId,
                displayName = coupleInvite.ownerDisplayName,
                emoji = coupleInvite.ownerEmoji,
                couple = when (userCouple) {
                    UserCouple.None -> null
                    is UserCouple.Joined -> CoupleResponse.of(userCouple.couple)
                },
                pendingInvite = CreateCoupleInviteResponse(
                    inviteKey = coupleInvite.inviteKey,
                ),
            )
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
