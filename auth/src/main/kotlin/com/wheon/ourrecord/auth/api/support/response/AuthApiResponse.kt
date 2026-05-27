package com.wheon.ourrecord.auth.api.support.response

import com.wheon.ourrecord.auth.api.support.error.AuthErrorMessage
import com.wheon.ourrecord.auth.api.support.error.AuthErrorType

data class AuthApiResponse<T> private constructor(
    val data: T? = null,
    val error: AuthErrorMessage? = null,
) {
    companion object {
        fun success(): AuthApiResponse<Any> {
            return AuthApiResponse()
        }

        fun <S> success(data: S): AuthApiResponse<S> {
            return AuthApiResponse(data)
        }

        fun <S> error(errorType: AuthErrorType): AuthApiResponse<S> {
            return AuthApiResponse(null, AuthErrorMessage(errorType))
        }
    }
}
