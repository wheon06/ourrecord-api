package com.wheon.ourrecord.support.file

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.storage.db.s3.S3Uploader
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileUploader(
    private val s3Uploader: S3Uploader,
    private val mediaAssetUrlResolver: MediaAssetUrlResolver,
) {
    fun uploadFile(resourceType: ResourceType, file: MultipartFile): UploadResult {
        val objectKey = s3Uploader.uploadFile(
            objectKey = ObjectKeyGenerator.generate(file, resourceType),
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
