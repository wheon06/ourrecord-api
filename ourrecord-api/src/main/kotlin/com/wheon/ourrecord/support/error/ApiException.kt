package com.wheon.ourrecord.support.error

class ApiException(
    val errorType: ErrorType,
) : RuntimeException(errorType.message)
