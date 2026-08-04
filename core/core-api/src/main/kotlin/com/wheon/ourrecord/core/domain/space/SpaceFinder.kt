package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.SpaceRepository
import org.springframework.stereotype.Component

@Component
class SpaceFinder(
    private val spaceRepository: SpaceRepository,
) {
    fun find(spaceId: Long): Space {
        val found = spaceRepository.findByIdAndStatus(spaceId, EntityStatus.ACTIVE)
            ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        return Space(
            id = found.id,
            userId = found.userId,
            anniversaryDate = found.anniversaryDate,
        )
    }
}
