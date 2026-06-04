package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "couple")
class CoupleEntity(
    val anniversaryDate: LocalDate,
    val ownerId: Long,
    state: CoupleState,
) : BaseEntity() {
    @Enumerated(EnumType.STRING)
    var state = state
        protected set
}
