package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class RecordImageValidator {
    companion object {
        private val ALLOWED_SIGNATURES = listOf(
            byteArrayOf(0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte()),
            byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte()),
            byteArrayOf(0x47.toByte(), 0x49.toByte(), 0x46.toByte(), 0x38.toByte()),
        )
    }

    fun validate(file: MultipartFile) {
        if (file.isEmpty) {
            throw ApiException(ErrorType.RECORD_IMAGE_INVALID)
        }

        val header = file.inputStream.use { it.readNBytes(4) }
        val signatureMatches = ALLOWED_SIGNATURES.any { signature ->
            header.size >= signature.size &&
                signature.indices.all { index -> header[index] == signature[index] }
        }
        if (!signatureMatches) {
            throw ApiException(ErrorType.RECORD_IMAGE_INVALID)
        }
    }
}
