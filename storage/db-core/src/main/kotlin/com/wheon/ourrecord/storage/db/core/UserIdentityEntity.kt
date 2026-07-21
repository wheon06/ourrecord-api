package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.IdentityProviderType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "user_identity",
    indexes = [
        Index(name = "udx_user_identity_provider_subject", columnList = "providerType, providerSubject", unique = true),
    ],
)
class UserIdentityEntity(
    @Column(name = "user_id")
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val providerType: IdentityProviderType,
    val providerSubject: String,
    val linkedAt: LocalDateTime,
) : BaseIdEntity() {
    @ManyToOne
    @JoinColumn(
        name = "user_id",
        insertable = false,
        updatable = false,
    )
    val user: UserEntity? = null
}
