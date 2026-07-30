package com.wheon.ourrecord.core.support.file

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.storage.db.s3.S3Uploader
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileUploader(
    private val s3Uploader: S3Uploader,
) {
    fun uploadFile(resourceType: ResourceType, file: MultipartFile): UploadResult {
        return UploadResult(
            path = s3Uploader.uploadFile(
                file = file,
                bucket = StorageServe.BUCKET,
                objectKey = ObjectKeyGenerator.generate(file, resourceType),
            ),
        )
    }
}
