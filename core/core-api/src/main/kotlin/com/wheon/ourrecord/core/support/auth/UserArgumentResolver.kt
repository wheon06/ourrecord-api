package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.domain.user.User
import com.wheon.ourrecord.support.auth.token.AuthKeyManager
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import kotlin.jvm.java

@Component
class UserArgumentResolver(
    private val authKeyManager: AuthKeyManager,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == User::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): User {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java) ?: throw ApiException(ErrorType.INVALID_REQUEST)

        val authKey = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?.takeIf { it.startsWith("Bearer ") }
            ?.substringAfter(' ')
            ?: throw ApiException(ErrorType.AUTHENTICATED_SESSION_EXPIRED)

        return User(
            id = authKeyManager.verify(authKey),
        )
    }
}
