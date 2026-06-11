package com.wheon.ourrecord.api.assembler

import com.wheon.ourrecord.domain.record.AddRecord
import com.wheon.ourrecord.domain.record.RecordService
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.file.ImageHandle
import org.springframework.stereotype.Component

@Component
class RecordAssembler(
    private val recordService: RecordService,
) {
    fun addRecord(apiCoupleUser: ApiCoupleUser, addRecord: AddRecord, imageHandle: ImageHandle): Long {
        return recordService.create(apiCoupleUser, addRecord, imageHandle)
    }
}
