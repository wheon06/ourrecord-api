package com.wheon.ourrecord.support.file

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.util.StringUtils
import java.util.UUID

object ObjectKeyGenerator {
    const val BUCKET_NAME = "ourrecord"

    fun generate(fileName: String, resourceType: ResourceType): String {
        val generatedName = UUID.randomUUID().toString().replace("-", "")
        val extension = StringUtils.getFilenameExtension(fileName)
            ?: throw ApiException(ErrorType.RECORD_IMAGE_INVALID)
        return "${resourceType.resourceName}/$generatedName.$extension"
    }
}
