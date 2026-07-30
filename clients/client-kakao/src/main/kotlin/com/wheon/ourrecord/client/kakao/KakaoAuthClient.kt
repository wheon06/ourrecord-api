package com.wheon.ourrecord.client.kakao

import com.wheon.ourrecord.client.kakao.model.KakaoClientTokenResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KakaoAuthClient internal constructor(
    @param:Value($$"${kakao-api.client-id}") private val clientId: String,
    @param:Value($$"${kakao-api.auth.login.redirect-uri}") private val redirectUri: String,
    @param:Value($$"${kakao-api.auth.login.client-secret}") private val clientSecret: String,
    private val kakaoAuthApi: KakaoAuthApi,
) {
    fun getToken(code: String): KakaoClientTokenResult {
        return kakaoAuthApi.getToken(
            clientId = clientId,
            redirectUri = redirectUri,
            code = code,
            clientSecret = clientSecret,
        ).toResult()
    }
}
