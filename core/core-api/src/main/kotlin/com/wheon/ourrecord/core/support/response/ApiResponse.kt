package com.wheon.ourrecord.core.support.response

import com.wheon.ourrecord.core.support.error.ErrorMessage
import com.wheon.ourrecord.core.support.error.ErrorType

data class ApiResponse<T> private constructor(
    val data: T? = null,
    val error: ErrorMessage? = null,
) {
    companion object {
        fun success(): ApiResponse<Any> {
            return ApiResponse()
        }

        fun <S> success(data: S): ApiResponse<S> {
            return ApiResponse(data)
        }

        fun <S> error(errorType: ErrorType): ApiResponse<S> {
            return ApiResponse(null, ErrorMessage(errorType))
        }
    }
}
