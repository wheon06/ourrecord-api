package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.storage.db.core.SpaceEntity
import com.wheon.ourrecord.storage.db.core.SpaceRepository
import org.springframework.stereotype.Component

@Component
class CoupleCreator(
    private val spaceRepository: SpaceRepository,
) {
    fun create(ownerUserId: Long, partnerUserId: Long): Long {
        val savedCouple = spaceRepository.save(
            SpaceEntity(
                userId = 1L,
            ),
        )
        return savedCouple.id
    }
}
