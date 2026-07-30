package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "space_member")
class MemberEntity(
    val spaceId: Long,
    val userId: Long,
    val nickname: String,
    val emoji: String,
) : BaseEntity()
