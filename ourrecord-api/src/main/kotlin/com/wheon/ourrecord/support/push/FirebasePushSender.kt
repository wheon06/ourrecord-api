package com.wheon.ourrecord.support.push

import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component

@Component
class FirebasePushSender(
    private val firebaseMessagingProvider: ObjectProvider<FirebaseMessaging>,
    private val properties: FirebaseProperties,
) : PushSender {
    override fun send(message: PushMessage): PushSendResult {
        if (!properties.enabled) {
            return PushSendResult.failed("PUSH_DISABLED")
        }

        val firebaseMessaging = firebaseMessagingProvider.ifAvailable
            ?: return PushSendResult.failed("FIREBASE_NOT_CONFIGURED")

        val firebaseMessage = Message.builder()
            .setToken(message.token)
            .setNotification(
                Notification.builder()
                    .setTitle(message.title)
                    .setBody(message.body)
                    .build(),
            )
            .putAllData(message.data)
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(
                        Aps.builder()
                            .setSound("default")
                            .build(),
                    )
                    .build(),
            )
            .build()

        return try {
            PushSendResult.sent(firebaseMessaging.send(firebaseMessage))
        } catch (exception: FirebaseMessagingException) {
            PushSendResult.failed(exception.messagingErrorCode?.name ?: exception.errorCode?.name ?: "FCM_SEND_FAILED")
        } catch (exception: RuntimeException) {
            PushSendResult.failed(exception.javaClass.simpleName.ifBlank { "FCM_SEND_FAILED" })
        }
    }
}
