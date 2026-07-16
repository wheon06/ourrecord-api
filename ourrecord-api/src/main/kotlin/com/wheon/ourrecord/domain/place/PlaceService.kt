package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.domain.record.RecordReader
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val couplePlaceManager: CouplePlaceManager,
    private val couplePlaceReader: CouplePlaceReader,
    private val placeCategoryReader: PlaceCategoryReader,
    private val recordReader: RecordReader,
)
