package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.Couple
import com.wheon.ourrecord.domain.CoupleInvite
import com.wheon.ourrecord.domain.UserCouple
import java.time.LocalDate

data class UserMeResponse(
    val userId: Long,
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
                fun of(member: com.wheon.ourrecord.domain.CoupleMember): CoupleMemberResponse {
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
