package com.wheon.ourrecord.core.support.push

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.ByteArrayInputStream
import java.util.Base64

@Configuration
class FirebaseConfig(
    private val properties: FirebaseProperties,
) {
    @Bean
    @ConditionalOnProperty(prefix = "firebase", name = ["enabled"], havingValue = "true")
    fun firebaseApp(): FirebaseApp {
        if (FirebaseApp.getApps().isNotEmpty()) {
            return FirebaseApp.getInstance()
        }

        val builder = FirebaseOptions.builder()
            .setCredentials(credentials())

        properties.projectId.trim()
            .takeIf { it.isNotBlank() }
            ?.let { builder.setProjectId(it) }

        return FirebaseApp.initializeApp(builder.build())
    }

    @Bean
    @ConditionalOnProperty(prefix = "firebase", name = ["enabled"], havingValue = "true")
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }

    private fun credentials(): GoogleCredentials {
        val serviceAccountBase64 = properties.serviceAccountBase64.trim()
        if (serviceAccountBase64.isBlank()) {
            throw IllegalStateException("FIREBASE_SERVICE_ACCOUNT_BASE64 is required")
        }

        val decoded = Base64.getDecoder().decode(serviceAccountBase64)
        return GoogleCredentials.fromStream(ByteArrayInputStream(decoded))
    }
}
