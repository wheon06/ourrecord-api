package com.wheon.ourrecord.auth.api.controller

import com.wheon.ourrecord.auth.api.support.error.AuthException
import com.wheon.ourrecord.auth.api.support.response.AuthApiResponse
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.wheon.ourrecord.auth"])
class AuthApiControllerAdvice {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(AuthException::class)
    fun handleAuthException(e: AuthException): ResponseEntity<AuthApiResponse<Any>> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error("AuthApiControllerAdvice : {}", e.message, e)
            LogLevel.WARN -> log.warn("AuthApiControllerAdvice : {}", e.message, e)
            else -> log.info("AuthApiControllerAdvice : {}", e.message, e)
        }
        return ResponseEntity(AuthApiResponse.error(e.errorType), e.errorType.status)
    }
}
