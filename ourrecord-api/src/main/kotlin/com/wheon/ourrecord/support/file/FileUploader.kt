package com.wheon.ourrecord.support.file

import com.wheon.ourrecord.core.enums.MediaAssetState
import com.wheon.ourrecord.core.enums.StorageProviderType
import com.wheon.ourrecord.storage.db.core.MediaAssetEntity
import com.wheon.ourrecord.storage.db.core.MediaAssetRepository
import com.wheon.ourrecord.storage.db.s3.S3Uploader
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.UUID
import javax.imageio.ImageIO

@Component
class FileUploader(
    @param:Value($$"${s3.bucket}") private val bucketName: String,
    private val s3Uploader: S3Uploader,
    private val mediaAssetRepository: MediaAssetRepository,
    private val mediaAssetUrlResolver: MediaAssetUrlResolver,
) {
    fun uploadFile(
        apiCoupleUser: ApiCoupleUser,
        file: MultipartFile,
    ): UploadResult {
        val image = ImageIO.read(file.inputStream) ?: throw ApiException(ErrorType.INVALID_REQUEST)
        val objectKey = uploadToS3(file)

        val mediaAsset = MediaAssetEntity(
            coupleId = apiCoupleUser.coupleId,
            ownerMemberId = apiCoupleUser.memberId,
            state = MediaAssetState.READY,
            storageProvider = StorageProviderType.S3,
            bucket = bucketName,
            objectKey = objectKey,
            originalFileName = file.originalFilename ?: "unknown",
            mimeType = file.contentType ?: "application/octet-stream",
            byteSize = file.size,
            width = image.width,
            height = image.height,
            failureReason = null,
        )
        val savedEntity = mediaAssetRepository.save(mediaAsset)

        return UploadResult(
            id = savedEntity.id,
            fileUrl = mediaAssetUrlResolver.resolve(savedEntity.bucket, savedEntity.objectKey),
        )
    }

    private fun uploadToS3(file: MultipartFile): String {
        val uniqueId = UUID.randomUUID().toString()
        val objectKey = "ourrecord/$uniqueId/${file.originalFilename ?: "unknown"}"
        return s3Uploader.uploadFile(file, objectKey)
    }
}
