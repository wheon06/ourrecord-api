package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.UserState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "`user`")
class UserEntity(
    @Enumerated(EnumType.STRING)
    val state: UserState,
    val nickname: String,
) : BaseEntity()
