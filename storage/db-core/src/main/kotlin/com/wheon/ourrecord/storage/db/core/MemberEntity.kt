package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "space_member")
class MemberEntity(
    val spaceId: Long,
    val userId: Long,
    nickname: String,
    emoji: String,
) : BaseEntity() {
    var nickname: String = nickname
        protected set

    var emoji: String = emoji
        protected set

    fun applyNickname(nickname: String) {
        this.nickname = nickname
    }

    fun applyEmoji(emoji: String) {
        this.emoji = emoji
    }
}
