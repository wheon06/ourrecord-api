package com.wheon.ourrecord.auth.api.support.error

data class AuthErrorMessage private constructor(
    val message: String,
) {
    constructor(authErrorType: AuthErrorType) : this(
        message = authErrorType.message,
    )
}
