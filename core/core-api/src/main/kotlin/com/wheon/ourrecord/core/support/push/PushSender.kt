package com.wheon.ourrecord.core.support.push

interface PushSender {
    fun send(message: PushMessage): PushSendResult
}
