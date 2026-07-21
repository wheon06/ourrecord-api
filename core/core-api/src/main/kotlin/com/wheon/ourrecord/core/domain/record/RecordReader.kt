package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.storage.db.core.RecordRepository
import org.springframework.stereotype.Component

@Component
class RecordReader(
    private val recordRepository: RecordRepository,
    private val recordMediaReader: RecordMediaReader,
)
