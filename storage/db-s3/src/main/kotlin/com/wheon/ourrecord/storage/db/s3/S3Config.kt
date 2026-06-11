package com.wheon.ourrecord.storage.db.s3

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import java.net.URI

@Configuration
class S3Config {
    @Value($$"${s3.endpoint}")
    private val endpoint: String? = null

    @Value($$"${s3.access-key}")
    private val accessKey: String? = null

    @Value($$"${s3.secret-key}")
    private val secretKey: String? = null

    fun staticCredentialsProvider(): StaticCredentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))

    fun s3Configuration(): S3Configuration = S3Configuration
        .builder()
        .pathStyleAccessEnabled(true)
        .build()

    @Bean
    fun s3Client(): S3Client = S3Client
        .builder()
        .httpClient(UrlConnectionHttpClient.builder().build())
        .credentialsProvider(staticCredentialsProvider())
        .region(Region.AP_NORTHEAST_2)
        .endpointOverride(URI.create(endpoint))
        .serviceConfiguration(s3Configuration())
        .overrideConfiguration(ClientOverrideConfiguration.builder().build())
        .build()

    @Bean
    fun s3Presigner(): S3Presigner = S3Presigner
        .builder()
        .credentialsProvider(staticCredentialsProvider())
        .region(Region.AP_NORTHEAST_2)
        .endpointOverride(URI.create(endpoint))
        .build()
}
