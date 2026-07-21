package com.wheon.ourrecord.core.support.file

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.core.support.file.MediaAssetUrlResolver
import com.wheon.ourrecord.core.support.file.ObjectKeyGenerator
import com.wheon.ourrecord.core.support.file.UploadResult
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
            file = file,
            bucket = ObjectKeyGenerator.BUCKET_NAME,
            objectKey = ObjectKeyGenerator.generate(file, resourceType),
        )

        return UploadResult(
            url = mediaAssetUrlResolver.resolve(
                bucket = ObjectKeyGenerator.BUCKET_NAME,
                objectKey = objectKey,
            ),
        )
    }
}
