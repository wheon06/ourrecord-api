package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "couple_invite",
    indexes = [
        Index(name = "udx_couple_invite_invite_key", columnList = "inviteKey", unique = true),
    ],
)
class CoupleInviteEntity(
    val coupleId: Long,
    val inviteKey: String,
) : BaseEntity()
