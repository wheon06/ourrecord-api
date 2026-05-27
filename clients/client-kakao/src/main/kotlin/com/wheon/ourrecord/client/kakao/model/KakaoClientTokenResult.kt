package com.wheon.ourrecord.client.kakao.model

import com.wheon.ourrecord.client.kakao.KakaoTokenResponseDto

data class KakaoClientTokenResult(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        internal fun of(dto: KakaoTokenResponseDto): KakaoClientTokenResult {
            if (dto.tokenType != "bearer") throw Exception("[Client-Kakao] tokenType 이 bearer 가 아닙니다.")
            return KakaoClientTokenResult(
                accessToken = dto.accessToken,
                refreshToken = dto.refreshToken,
            )
        }
    }
}
