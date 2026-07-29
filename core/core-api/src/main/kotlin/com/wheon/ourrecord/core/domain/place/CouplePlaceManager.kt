package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.storage.db.core.PlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceSourceRefRepository
import com.wheon.ourrecord.storage.db.core.SpacePlaceRepository
import org.springframework.stereotype.Component

@Component
class CouplePlaceManager(
    private val placeRepository: PlaceRepository,
    private val placeSourceRefRepository: PlaceSourceRefRepository,
    private val spacePlaceRepository: SpacePlaceRepository,
)
