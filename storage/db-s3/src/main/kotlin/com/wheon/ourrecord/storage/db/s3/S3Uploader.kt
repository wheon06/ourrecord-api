package com.wheon.ourrecord.storage.db.s3

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Component
class S3Uploader(
    private val s3Client: S3Client,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Value($$"${s3.bucket}")
    private val bucket: String? = null

    fun uploadFile(
        file: MultipartFile,
        objectKey: String,
    ): String {
        s3Client.putObject(
            PutObjectRequest
                .builder()
                .bucket(bucket)
                .key(objectKey)
                .contentType(file.contentType)
                .build(),
            RequestBody.fromInputStream(file.inputStream, file.size),
        )

        return objectKey
    }
}
