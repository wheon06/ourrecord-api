package com.wheon.ourrecord.support.file

import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

class ObjectKeyGenerator {
    companion object {
        private val KST: ZoneId = ZoneId.of("Asia/Seoul")
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE

        const val BUCKET_NAME = "ourrecord"

        fun generate(file: MultipartFile, resourceType: ResourceType): String {
            val date = LocalDate.now(KST).format(DATE_FORMATTER)
            val generatedName = UUID.randomUUID().toString().replace("-", "")
            val extension = StringUtils.getFilenameExtension(file.originalFilename)
                ?: throw ApiException(ErrorType.RECORD_IMAGE_INVALID)
            return "${resourceType.resourceName}/$date/$generatedName.$extension"
        }
    }
}
