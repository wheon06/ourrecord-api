package com.wheon.ourrecord.domain.letter

import com.wheon.ourrecord.support.ApiCoupleUser
import org.springframework.stereotype.Service

@Service
class LetterService(
    private val letterReader: LetterReader,
) {
    fun getLetters(apiCoupleUser: ApiCoupleUser): List<Letter> {
        return letterReader.getLetters(apiCoupleUser.coupleId)
    }

    fun getLatestLetter(apiCoupleUser: ApiCoupleUser): Letter? {
        return letterReader.getLatestLetter(apiCoupleUser.coupleId)
    }
}
