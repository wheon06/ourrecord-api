package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.storage.db.core.CouplePlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceSourceRefRepository
import org.springframework.stereotype.Component

@Component
class CouplePlaceManager(
    private val placeRepository: PlaceRepository,
    private val placeSourceRefRepository: PlaceSourceRefRepository,
    private val couplePlaceRepository: CouplePlaceRepository,
)
