package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "couple")
class CoupleEntity(
    val ownerUserId: Long,
    val partnerUserId: Long,
) : BaseEntity()
