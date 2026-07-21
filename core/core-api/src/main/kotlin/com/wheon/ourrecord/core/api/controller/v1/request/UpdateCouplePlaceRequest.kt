package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType

data class UpdateCouplePlaceRequest(
    val categoryCode: String?,
) {
    fun validate() {
        if (categoryCode?.isBlank() == true) throw ApiException(ErrorType.INVALID_REQUEST)
    }
}
