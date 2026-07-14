package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "couple_place")
class CouplePlaceEntity(
    val coupleId: Long,
    val placeId: Long,
    categoryCode: String?,
    val savedByMemberId: Long,
) : BaseEntity() {
    var categoryCode: String? = categoryCode
        protected set

    fun changeCategory(categoryCode: String?) {
        this.categoryCode = categoryCode
    }
}
