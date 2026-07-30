package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.storage.db.core.RecordMediaRepository
import org.springframework.stereotype.Component

@Component
class RecordMediaFinder(
    private val recordMediaRepository: RecordMediaRepository,
)
