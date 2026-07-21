package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.domain.record.RecordMediaReader
import com.wheon.ourrecord.storage.db.core.CouplePlaceRepository
import org.springframework.stereotype.Component

@Component
class CouplePlaceReader(
    private val couplePlaceRepository: CouplePlaceRepository,
    private val recordMediaReader: RecordMediaReader,
)
