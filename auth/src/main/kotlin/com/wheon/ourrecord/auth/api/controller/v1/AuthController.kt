package com.wheon.ourrecord.auth.api.controller.v1

import com.wheon.ourrecord.auth.api.support.response.AuthApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    @PostMapping("/v1/auth/kakao")
    fun kakaoLogin(): AuthApiResponse<Any> {
        return AuthApiResponse.success()
    }
}
