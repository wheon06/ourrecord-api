package com.wheon.ourrecord.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface UserDeviceRepository : JpaRepository<UserDeviceEntity, Long>
