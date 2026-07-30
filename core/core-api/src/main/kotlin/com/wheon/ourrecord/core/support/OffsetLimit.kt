package com.wheon.ourrecord.core.support

import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class OffsetLimit(
    val offset: Int,
    val limit: Int,
) {
    constructor(offset: Int?, limit: Int?) : this(
        offset ?: 0,
        limit ?: 10,
    )

    init {
        if (offset < 0) throw CoreException(ErrorType.INVALID_REQUEST)
        if (limit < 1) throw CoreException(ErrorType.INVALID_REQUEST)
    }

    fun toPageable(): Pageable {
        val offset = offset
        val limit = limit
        return PageRequest.of(offset / limit, limit)
    }
}
