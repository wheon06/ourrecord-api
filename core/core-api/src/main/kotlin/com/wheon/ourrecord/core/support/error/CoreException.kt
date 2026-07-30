package com.wheon.ourrecord.core.support.error

class CoreException(
    val errorType: ErrorType,
) : RuntimeException(errorType.message)
