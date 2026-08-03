package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "record")
class RecordEntity(
    val spaceId: Long,
    val memberId: Long,
    val placeId: Long,
    title: String,
    content: String,
    visitedOn: LocalDate,
    thumbnailUrl: String,
) : BaseEntity() {
    var title: String = title
        protected set
    var content: String = content
        protected set
    var visitedOn: LocalDate = visitedOn
        protected set
    var thumbnailUrl: String = thumbnailUrl
        protected set

    fun updateContent(title: String, content: String, visitedOn: LocalDate) {
        this.title = title
        this.content = content
        this.visitedOn = visitedOn
    }

    fun applyThumbnailUrl(thumbnailUrl: String) {
        this.thumbnailUrl = thumbnailUrl
    }
}
