package com.wheon.ourrecord.client.kakao

import com.wheon.ourrecord.client.kakao.model.KakaoClientTokenResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KakaoClient internal constructor(
    @param:Value($$"${kakao.client-id}") private val clientId: String,
    @param:Value($$"${kakao.login.redirect-uri}") private val redirectUri: String,
    @param:Value($$"${kakao.login.client-secret}") private val clientSecret: String,
    private val kakaoApi: KakaoApi,
) {
    fun getToken(code: String): KakaoClientTokenResult {
        return kakaoApi.getToken(
            clientId = clientId,
            redirectUri = redirectUri,
            code = code,
            clientSecret = clientSecret,
        ).toResult()
    }
}
