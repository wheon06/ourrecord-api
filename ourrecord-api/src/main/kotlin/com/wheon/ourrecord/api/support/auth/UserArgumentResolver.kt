package com.wheon.ourrecord.api.support.auth

import com.wheon.ourrecord.api.support.error.ApiException
import com.wheon.ourrecord.api.support.error.ErrorType
import com.wheon.ourrecord.domain.User
import com.wheon.ourrecord.storage.db.core.UserRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import kotlin.jvm.java

@Component
class UserArgumentResolver(
    private val userRepository: UserRepository,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == User::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): User {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java) ?: throw ApiException(ErrorType.INVALID_REQUEST)

        val token = resolveToken(request) ?: throw ApiException(ErrorType.INVALID_TOKEN)
        if (token.isBlank()) throw ApiException(ErrorType.INVALID_TOKEN)

        return User(
            id = 1L,
        )
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }
}
