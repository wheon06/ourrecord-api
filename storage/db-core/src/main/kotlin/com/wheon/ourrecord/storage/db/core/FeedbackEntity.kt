package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "feedback")
class FeedbackEntity(
    val userId: Long,
    val content: String,
) : BaseNoStatusEntity()
