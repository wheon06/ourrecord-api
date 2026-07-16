package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.response.AuthTokenResponse
import com.wheon.ourrecord.support.auth.SnsLoginService
import com.wheon.ourrecord.support.auth.token.AuthKeyManager
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val snsLoginService: SnsLoginService,
    private val authKeyManager: AuthKeyManager,
) {
//    @GetMapping("/sns/login/kakao/callback")
//    fun kakaoLoginCallback(
//        @RequestParam code: String,
//    ): ApiResponse<AuthTokenResponse> {
//        snsLoginService.kakaoLogin(code)
//    }
}
