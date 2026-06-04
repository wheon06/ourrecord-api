package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleMemberRole
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "couple_member",
    indexes = [
        Index(name = "udx_couple_member_user_id_couple_id", columnList = "userId, coupleId", unique = true),
    ],
)
class CoupleMemberEntity(
    val coupleId: Long,
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val role: CoupleMemberRole,
    val displayName: String,
    emoji: String,
) : BaseEntity() {
    var emoji: String = emoji
        protected set

    fun updateEmoji(newEmoji: String) {
        this.emoji = newEmoji
    }
}
