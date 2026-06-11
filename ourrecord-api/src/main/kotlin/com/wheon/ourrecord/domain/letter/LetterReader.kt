package com.wheon.ourrecord.domain.letter

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.LetterState
import com.wheon.ourrecord.storage.db.core.LetterRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class LetterReader(
    private val letterRepository: LetterRepository,
) {
    fun getLetters(coupleId: Long): List<Letter> {
        return getLetters(coupleId = coupleId, size = LETTER_LIST_SIZE)
    }

    fun getLatestLetter(coupleId: Long): Letter? {
        return getLetters(coupleId = coupleId, size = 1).firstOrNull()
    }

    private fun getLetters(coupleId: Long, size: Int): List<Letter> {
        return letterRepository.findLetterRows(
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
            excludedState = LetterState.CANCELLED,
            pageable = PageRequest.of(0, size),
        ).map {
            Letter(
                id = it.id,
                senderName = it.senderName,
                content = it.content,
                revealDate = it.revealAt.toLocalDate(),
                createdAt = it.createdAt,
                coupleId = it.coupleId,
                senderUserId = it.senderUserId,
            )
        }
    }

    companion object {
        private const val LETTER_LIST_SIZE = 100
    }
}
