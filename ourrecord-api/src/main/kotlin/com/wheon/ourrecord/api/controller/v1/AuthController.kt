package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.storage.db.core.AuthKeyRepository
import com.wheon.ourrecord.support.auth.SnsLoginService
import com.wheon.ourrecord.support.auth.token.AuthKeyManager
import com.wheon.ourrecord.support.response.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val snsLoginService: SnsLoginService,
    private val authKeyManager: AuthKeyManager,
    private val authKeyRepository: AuthKeyRepository,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

//    @GetMapping("/sns/login/kakao/callback")
//    fun kakaoLoginCallback(
//        @RequestParam code: String,
//    ): ApiResponse<AuthTokenResponse> {
//        snsLoginService.kakaoLogin(code)
//    }

    @GetMapping("/test")
    fun test(): ApiResponse<Any> {
        authKeyRepository.findAll()
        authKeyRepository.findAll(Pageable.unpaged())
        return ApiResponse.success()
    }
}
