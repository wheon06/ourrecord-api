package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.domain.couple.CoupleFinder
import com.wheon.ourrecord.domain.couple.UserCouple
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.auth.token.TokenManager
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class ApiCoupleUserArgumentResolver(
    private val tokenManager: TokenManager,
    private val coupleFinder: CoupleFinder,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == ApiCoupleUser::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): ApiCoupleUser {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java) ?: throw ApiException(ErrorType.INVALID_REQUEST)

        val token = resolveToken(request) ?: throw ApiException(ErrorType.INVALID_TOKEN)
        if (token.isBlank()) throw ApiException(ErrorType.INVALID_TOKEN)

        val claims = tokenManager.getClaims(token)
        if (claims["tokenType"] != "ACCESS") throw ApiException(ErrorType.INVALID_TOKEN)

        val userId = claims.subject.toLong()
        return when (val userCouple = coupleFinder.findUserCouple(userId)) {
            UserCouple.None -> throw ApiException(ErrorType.NOT_FOUND_DATA)
            is UserCouple.Joined -> ApiCoupleUser(
                userId = userId,
                memberId = userCouple.memberId,
                coupleId = userCouple.couple.id,
            )
        }
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }
}
