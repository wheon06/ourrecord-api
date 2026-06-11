package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.RecordState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "record")
class RecordEntity(
    val coupleId: Long,
    val authorMemberId: Long,
    couplePlaceId: Long,
    title: String,
    content: String,
    visitedOn: LocalDate,
    state: RecordState,
) : BaseEntity() {
    var couplePlaceId: Long = couplePlaceId
        protected set

    var title: String = title
        protected set

    var content: String = content
        protected set

    var visitedOn: LocalDate = visitedOn
        protected set

    @Enumerated(EnumType.STRING)
    var state: RecordState = state
        protected set

    fun update(couplePlaceId: Long, title: String, content: String, visitedOn: LocalDate) {
        this.couplePlaceId = couplePlaceId
        this.title = title
        this.content = content
        this.visitedOn = visitedOn
    }

    fun archived() {
        state = RecordState.ARCHIVED
    }
}
