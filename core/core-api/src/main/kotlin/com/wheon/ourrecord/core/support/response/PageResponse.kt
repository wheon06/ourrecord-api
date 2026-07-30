package com.wheon.ourrecord.core.support.response

data class PageResponse<T>(
    val results: List<T>,
    val hasNext: Boolean,
)
