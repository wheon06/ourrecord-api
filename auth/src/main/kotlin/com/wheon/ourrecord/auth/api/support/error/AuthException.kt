package com.wheon.ourrecord.auth.api.support.error

class AuthException(
    val errorType: AuthErrorType,
) : RuntimeException(errorType.message)
