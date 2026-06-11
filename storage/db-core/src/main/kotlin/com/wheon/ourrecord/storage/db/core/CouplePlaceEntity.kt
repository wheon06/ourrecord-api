package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CouplePlaceVisibility
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "couple_place")
class CouplePlaceEntity(
    val coupleId: Long,
    val placeId: Long,
    categoryCode: String?,
    val savedByMemberId: Long,
    @Enumerated(EnumType.STRING)
    val visibility: CouplePlaceVisibility,
) : BaseEntity() {
    var categoryCode: String? = categoryCode
        protected set

    fun changeCategory(categoryCode: String?) {
        this.categoryCode = categoryCode
    }
}
