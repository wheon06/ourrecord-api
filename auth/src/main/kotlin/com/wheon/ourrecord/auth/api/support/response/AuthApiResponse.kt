package com.wheon.ourrecord.auth.api.support.response

import com.wheon.ourrecord.api.support.error.ErrorMessage
import com.wheon.ourrecord.api.support.error.ErrorType

data class AuthApiResponse<T> private constructor(
    val data: T? = null,
    val error: ErrorMessage? = null,
) {
    companion object {
        fun success(): AuthApiResponse<Any> {
            return AuthApiResponse()
        }

        fun <S> success(data: S): AuthApiResponse<S> {
            return AuthApiResponse(data)
        }

        fun <S> error(errorType: ErrorType): AuthApiResponse<S> {
            return AuthApiResponse(null, ErrorMessage(errorType))
        }
    }
}
