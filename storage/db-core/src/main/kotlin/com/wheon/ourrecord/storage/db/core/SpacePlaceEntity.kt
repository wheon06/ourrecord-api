package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "space_place")
class SpacePlaceEntity(
    val userId: Long,
    val spaceId: Long,
    val placeId: Long,
    categoryCode: String?,
) : BaseEntity() {
    var categoryCode: String? = categoryCode
        protected set

    fun changeCategory(categoryCode: String?) {
        this.categoryCode = categoryCode
    }
}
