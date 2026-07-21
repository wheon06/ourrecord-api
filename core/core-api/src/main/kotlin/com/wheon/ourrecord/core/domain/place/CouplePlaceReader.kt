package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.domain.record.RecordMediaReader
import com.wheon.ourrecord.storage.db.core.CouplePlaceRepository
import org.springframework.stereotype.Component

@Component
class CouplePlaceReader(
    private val couplePlaceRepository: CouplePlaceRepository,
    private val recordMediaReader: RecordMediaReader,
)
