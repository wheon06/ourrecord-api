package com.wheon.ourrecord.client.kakao

import com.fasterxml.jackson.annotation.JsonProperty
import com.wheon.ourrecord.client.kakao.model.KakaoClientTokenResult

internal data class KakaoTokenResponseDto(
    @param:JsonProperty("token_type")
    val tokenType: String,
    @param:JsonProperty("access_token")
    val accessToken: String,
    @param:JsonProperty("expires_in")
    val expiresIn: Int,
    @param:JsonProperty("refresh_token")
    val refreshToken: String,
    @param:JsonProperty("refresh_token_expires_in")
    val refreshTokenExpiresIn: Int,
) {
    fun toResult(): KakaoClientTokenResult {
        return KakaoClientTokenResult.of(this)
    }
}
