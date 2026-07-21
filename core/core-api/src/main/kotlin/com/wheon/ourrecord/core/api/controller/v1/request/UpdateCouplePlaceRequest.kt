package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType

data class UpdateCouplePlaceRequest(
    val categoryCode: String?,
) {
    fun validate() {
        if (categoryCode?.isBlank() == true) throw CoreException(ErrorType.INVALID_REQUEST)
    }
}
