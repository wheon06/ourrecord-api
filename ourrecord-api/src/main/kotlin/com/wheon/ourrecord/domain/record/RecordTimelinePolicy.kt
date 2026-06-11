package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType

object RecordTimelinePolicy {
    const val DEFAULT_SIZE = 30
    const val MAX_SIZE = 100
    const val EXTRA_ROW_SIZE = 1

    fun normalizeSize(size: Int?): Int {
        val normalizedSize = size ?: DEFAULT_SIZE
        if (normalizedSize < 1) throw ApiException(ErrorType.INVALID_REQUEST)
        if (normalizedSize > MAX_SIZE) return MAX_SIZE

        return normalizedSize
    }
}
