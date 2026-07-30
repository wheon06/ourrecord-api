package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.storage.db.core.SpaceRepository
import org.springframework.stereotype.Component

@Component
class SpaceFinder(
    private val spaceRepository: SpaceRepository,
)
