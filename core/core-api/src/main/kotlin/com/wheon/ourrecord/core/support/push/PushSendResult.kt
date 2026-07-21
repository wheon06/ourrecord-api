package com.wheon.ourrecord.support.push

data class PushSendResult(
    val sent: Boolean,
    val providerMessageId: String?,
    val failureCode: String?,
) {
    companion object {
        fun sent(providerMessageId: String): PushSendResult {
            return PushSendResult(
                sent = true,
                providerMessageId = providerMessageId,
                failureCode = null,
            )
        }

        fun failed(failureCode: String): PushSendResult {
            return PushSendResult(
                sent = false,
                providerMessageId = null,
                failureCode = failureCode,
            )
        }
    }
}
