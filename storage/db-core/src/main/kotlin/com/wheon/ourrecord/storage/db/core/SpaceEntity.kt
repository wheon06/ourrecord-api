package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "space")
class SpaceEntity(
    val userId: Long,
) : BaseEntity()
