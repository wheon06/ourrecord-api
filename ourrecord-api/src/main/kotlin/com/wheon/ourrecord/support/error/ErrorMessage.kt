package com.wheon.ourrecord.support.error

data class ErrorMessage private constructor(
    val message: String,
) {
    constructor(errorType: ErrorType) : this(
        message = errorType.message,
    )
}
