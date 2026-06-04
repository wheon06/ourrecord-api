package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.Couple

data class CoupleResponse(
    val id: Long,
) {
    companion object {
        fun of(couple: Couple): CoupleResponse {
            return CoupleResponse(
                id = couple.id,
            )
        }
    }
}
