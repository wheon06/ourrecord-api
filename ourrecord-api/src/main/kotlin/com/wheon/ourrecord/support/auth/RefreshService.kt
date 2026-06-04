package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.support.auth.token.IssuedToken
import com.wheon.ourrecord.support.auth.token.TokenManager
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Service

@Service
class RefreshService(
    private val tokenManager: TokenManager,
) {
    fun refresh(refreshToken: String): IssuedToken {
        val token = tokenManager.getClaims(refreshToken)
        if (token["tokenType"] != "REFRESH") throw ApiException(ErrorType.INVALID_TOKEN)

        return tokenManager.issue(token.subject.toLong())
    }
}
