package com.wheon.ourrecord.core.support

data class Page<T>(
    val results: List<T>,
    val hasNext: Boolean,
)
