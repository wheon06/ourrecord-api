package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.letter.Letter
import java.time.format.DateTimeFormatter

data class LetterResponse(
    val id: Long,
    val senderName: String,
    val content: String,
    val revealDate: String,
    val createdAt: String,
    val coupleId: Long,
    val senderId: Long,
) {
    companion object {
        fun of(letter: Letter): LetterResponse {
            return LetterResponse(
                id = letter.id,
                senderName = letter.senderName,
                content = letter.content,
                revealDate = letter.revealDate.toString(),
                createdAt = letter.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                coupleId = letter.coupleId,
                senderId = letter.senderUserId,
            )
        }

        fun of(letters: List<Letter>): List<LetterResponse> {
            return letters.map { of(it) }
        }
    }
}
