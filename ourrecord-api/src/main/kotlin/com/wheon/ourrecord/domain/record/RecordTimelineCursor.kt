package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import java.time.LocalDate
import java.util.Base64

data class RecordTimelineCursor(
    val visitedOn: LocalDate,
    val recordId: Long,
    val sortOrder: Int,
) {
    fun encode(): String {
        val value = "$visitedOn$DELIMITER$recordId$DELIMITER$sortOrder"
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.toByteArray(Charsets.UTF_8))
    }

    companion object {
        private const val DELIMITER = "|"
        private const val CURSOR_PART_SIZE = 3

        fun decode(cursor: String?): RecordTimelineCursor? {
            if (cursor.isNullOrBlank()) return null

            return runCatching {
                val decoded = String(Base64.getUrlDecoder().decode(cursor), Charsets.UTF_8)
                val parts = decoded.split(DELIMITER)
                if (parts.size != CURSOR_PART_SIZE) throw ApiException(ErrorType.INVALID_REQUEST)

                RecordTimelineCursor(
                    visitedOn = LocalDate.parse(parts[0]),
                    recordId = parts[1].toLong(),
                    sortOrder = parts[2].toInt(),
                )
            }.getOrElse {
                if (it is ApiException) throw it
                throw ApiException(ErrorType.INVALID_REQUEST)
            }
        }
    }
}
