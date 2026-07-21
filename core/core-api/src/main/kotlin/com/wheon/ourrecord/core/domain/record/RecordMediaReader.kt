package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.storage.db.core.RecordMediaRepository
import com.wheon.ourrecord.core.support.file.MediaAssetUrlResolver
import org.springframework.stereotype.Component

@Component
class RecordMediaReader(
    private val recordMediaRepository: RecordMediaRepository,
    private val mediaAssetUrlResolver: MediaAssetUrlResolver,
)
