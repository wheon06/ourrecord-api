package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.OSType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "auth_session")
class AuthSessionEntity(
    @Column(name = "user_id")
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val osType: OSType,
    val deviceId: String,
    val userAgent: String,
) : BaseIdEntity() {
    @ManyToOne
    @JoinColumn(
        name = "user_id",
        insertable = false,
        updatable = false,
    )
    val user: UserEntity? = null
}
