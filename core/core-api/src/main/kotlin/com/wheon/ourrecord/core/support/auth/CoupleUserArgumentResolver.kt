package com.wheon.ourrecord.core.support.auth

import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.core.domain.user.CoupleUser
import com.wheon.ourrecord.core.support.auth.token.AuthKeyManager
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class CoupleUserArgumentResolver(
    private val authKeyManager: AuthKeyManager,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == CoupleUser::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): CoupleUser {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java) ?: throw CoreException(ErrorType.INVALID_REQUEST)

        val authKey = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?.takeIf { it.startsWith("Bearer ") }
            ?.substringAfter(' ')
            ?: throw CoreException(ErrorType.AUTHENTICATED_SESSION_EXPIRED)

        return CoupleUser(
            userId = authKeyManager.verify(authKey),
            coupleId = 1L,
        )
    }
}
