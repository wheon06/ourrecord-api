package com.wheon.ourrecord.api.support.error

class ApiException(
    val errorType: ErrorType,
) : RuntimeException(errorType.message)
