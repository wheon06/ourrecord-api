package com.wheon.ourrecord.support.push

interface PushSender {
    fun send(message: PushMessage): PushSendResult
}
