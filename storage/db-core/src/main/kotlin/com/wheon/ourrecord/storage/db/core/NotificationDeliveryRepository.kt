package com.wheon.ourrecord.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface NotificationDeliveryRepository : JpaRepository<NotificationDeliveryEntity, Long>
