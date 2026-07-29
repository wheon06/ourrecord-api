package com.wheon.ourrecord.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface SpaceInviteRepository : JpaRepository<SpaceInviteEntity, Long> {
    fun findByInviteKey(inviteKey: String): SpaceInviteEntity?
}
