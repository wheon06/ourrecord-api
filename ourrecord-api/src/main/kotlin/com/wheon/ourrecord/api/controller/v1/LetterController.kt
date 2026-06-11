package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.response.LetterResponse
import com.wheon.ourrecord.domain.letter.LetterService
import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LetterController(
    private val letterService: LetterService,
) {
    @GetMapping("/v1/letters")
    fun getLetters(
        apiCoupleUser: ApiCoupleUser,
    ): ApiResponse<List<LetterResponse>> {
        return ApiResponse.success(
            LetterResponse.of(letterService.getLetters(apiCoupleUser)),
        )
    }

    @GetMapping("/v1/letters/latest")
    fun getLatestLetter(
        apiCoupleUser: ApiCoupleUser,
    ): ApiResponse<LetterResponse?> {
        return ApiResponse.success(
            letterService.getLatestLetter(apiCoupleUser)?.let { LetterResponse.of(it) },
        )
    }
}
