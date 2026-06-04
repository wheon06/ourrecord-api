package com.wheon.ourrecord.api.controller

import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import com.wheon.ourrecord.support.response.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.wheon.ourrecord"])
class ApiControllerAdvice {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<ApiResponse<Any>> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error("[ApiException] {}", e.errorType.message, e)
            LogLevel.WARN -> log.warn("[ApiException] {}", e.errorType.message, e)
            else -> log.info("[ApiException] {}", e.errorType.message, e)
        }
        return ResponseEntity(ApiResponse.error(e.errorType), e.errorType.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        log.error("Exception : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.status)
    }
}
