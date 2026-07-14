package com.wheon.ourrecord.support.file

import com.wheon.ourrecord.storage.db.s3.S3Uploader
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileUploader(
    private val s3Uploader: S3Uploader,
    private val mediaAssetUrlResolver: MediaAssetUrlResolver,
) {
    fun uploadFile(resourceType: ResourceType, file: MultipartFile): UploadResult {
        val originalFilename = file.originalFilename
            ?: throw ApiException(ErrorType.RECORD_IMAGE_INVALID)
        val objectKey = s3Uploader.uploadFile(
            objectKey = ObjectKeyGenerator.generate(originalFilename, resourceType),
            file = file,
        )

        return UploadResult(
            url = mediaAssetUrlResolver.resolve(
                bucket = ObjectKeyGenerator.BUCKET_NAME,
                objectKey = objectKey,
            ),
        )
    }
}
