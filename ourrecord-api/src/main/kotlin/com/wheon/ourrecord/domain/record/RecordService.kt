package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.domain.place.CouplePlaceManager
import com.wheon.ourrecord.domain.place.CouplePlaceReader
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.file.ImageHandle
import org.springframework.stereotype.Service

@Service
class RecordService(
    private val recordManager: RecordManager,
    private val couplePlaceManager: CouplePlaceManager,
    private val couplePlaceReader: CouplePlaceReader,
    private val recordMediaReader: RecordMediaReader,
) {
    fun create(apiCoupleUser: ApiCoupleUser, addRecord: AddRecord, imageHandle: ImageHandle): Long {
        val couplePlaceId = couplePlaceManager.findOrCreate(
            coupleId = apiCoupleUser.coupleId,
            memberId = apiCoupleUser.memberId,
            place = addRecord.place,
        )

        return recordManager.create(
            newRecord = NewRecord(
                coupleId = apiCoupleUser.coupleId,
                authorMemberId = apiCoupleUser.memberId,
                couplePlaceId = couplePlaceId,
                title = addRecord.title,
                content = addRecord.content,
                visitedOn = addRecord.visitedOn,
            ),
            imageHandle = imageHandle,
        )
    }

    fun createAtCouplePlace(apiCoupleUser: ApiCoupleUser, couplePlaceId: Long, newRecord: NewRecord, imageHandle: ImageHandle): Long {
        couplePlaceReader.checkActiveCouplePlace(
            coupleId = apiCoupleUser.coupleId,
            couplePlaceId = couplePlaceId,
        )

        return recordManager.create(
            newRecord = newRecord,
            imageHandle = imageHandle,
        )
    }

    fun update(apiCoupleUser: ApiCoupleUser, recordId: Long, updateRecord: UpdateRecord) {
        val couplePlaceId = couplePlaceManager.findOrCreate(
            coupleId = apiCoupleUser.coupleId,
            memberId = apiCoupleUser.memberId,
            place = updateRecord.place,
        )

        recordManager.update(
            coupleId = apiCoupleUser.coupleId,
            recordId = recordId,
            couplePlaceId = couplePlaceId,
            updateRecord = updateRecord,
        )
    }

    fun updateDetails(apiCoupleUser: ApiCoupleUser, recordId: Long, updateRecordDetails: UpdateRecordDetails) {
        recordManager.updateDetails(
            coupleId = apiCoupleUser.coupleId,
            recordId = recordId,
            updateRecordDetails = updateRecordDetails,
        )
    }

    fun delete(apiCoupleUser: ApiCoupleUser, recordId: Long) {
        recordManager.delete(
            coupleId = apiCoupleUser.coupleId,
            recordId = recordId,
        )
    }

    fun getTimeline(apiCoupleUser: ApiCoupleUser, size: Int?, cursor: String?): RecordTimeline {
        return recordMediaReader.getTimeline(
            coupleId = apiCoupleUser.coupleId,
            size = size,
            cursor = cursor,
        )
    }
}
