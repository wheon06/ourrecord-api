package com.wheon.ourrecord.client.kakao

import com.fasterxml.jackson.annotation.JsonProperty
import com.wheon.ourrecord.client.kakao.model.KakaoClientProfileResult

data class KakaoProfileResponseDto(
    val id: String,
    @param:JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount,
) {
    fun toResult(): KakaoClientProfileResult {
        return KakaoClientProfileResult(
            id = id,
            email = kakaoAccount.email,
        )
    }

    data class KakaoAccount(
        val email: String,
    )
}
