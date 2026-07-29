package com.wheon.ourrecord.core.domain.place

import com.wheon.ourrecord.core.domain.record.RecordMediaReader
import com.wheon.ourrecord.storage.db.core.SpacePlaceRepository
import org.springframework.stereotype.Component

@Component
class CouplePlaceReader(
    private val spacePlaceRepository: SpacePlaceRepository,
    private val recordMediaReader: RecordMediaReader,
)
