package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.domain.record.RecordReader
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val couplePlaceManager: CouplePlaceManager,
    private val couplePlaceReader: CouplePlaceReader,
    private val recordReader: RecordReader,
)
