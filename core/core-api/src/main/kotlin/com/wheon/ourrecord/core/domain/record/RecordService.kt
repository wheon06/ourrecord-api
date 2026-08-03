package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.space.SpaceVerifier
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.Page
import com.wheon.ourrecord.core.support.file.MediaHandle
import org.springframework.stereotype.Service

@Service
class RecordService(
    private val recordFinder: RecordFinder,
    private val recordManager: RecordManager,
    private val recordMediaReader: RecordMediaReader,
    private val recordAddPostProcessor: List<RecordAddPostProcess>,
    private val recordModifyPostProcessor: List<RecordModifyPostProcess>,
    private val spaceVerifier: SpaceVerifier,
) {
    fun create(
        user: User,
        spaceId: Long,
        target: RecordTarget,
        content: RecordContent,
        mediaHandle: MediaHandle,
    ): Long {
        val context = spaceVerifier.verify(user.id, spaceId)
        val record = recordManager.create(
            context = context,
            target = target,
            content = content,
            mediaHandle = mediaHandle,
        )
        recordAddPostProcessor.forEach { it.process(user, record) }
        return record.id
    }

    fun modify(
        user: User,
        spaceId: Long,
        recordId: Long,
        content: RecordContent,
        mediaHandle: MediaHandle,
    ): Long {
        val context = spaceVerifier.verify(user.id, spaceId)
        val record = recordManager.update(
            context = context,
            recordId = recordId,
            content = content,
            mediaHandle = mediaHandle,
        )
        recordModifyPostProcessor.forEach { it.process(user, record) }
        return record.id
    }

    fun getRecords(spaceId: Long, placeId: Long, offsetLimit: OffsetLimit): Page<Record> {
        return recordFinder.find(spaceId, placeId, offsetLimit)
    }

    fun findRecordMedia(records: List<Record>): Map<Long, List<RecordMedia>> {
        return recordMediaReader.readMediaMap(records)
    }
}
