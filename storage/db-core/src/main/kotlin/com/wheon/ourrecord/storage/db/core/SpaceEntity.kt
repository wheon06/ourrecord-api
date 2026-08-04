package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "space")
class SpaceEntity(
    val userId: Long,
    anniversaryDate: LocalDate?,
) : BaseEntity() {
    var anniversaryDate: LocalDate? = null
        protected set

    fun applyAnniversaryDate(date: LocalDate) {
        this.anniversaryDate = date
    }
}
