package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.SocialProviderType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_auth_identity")
class UserAuthIdentityEntity(
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val provider: SocialProviderType,
    val providerUserId: String,
    val linkedAt: LocalDateTime,
) : BaseIdEntity()
