package com.wheon.ourrecord.support.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val message: String, val logLevel: LogLevel) {
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다. 잠시 후 다시 시도해주세요.", LogLevel.ERROR),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "요청이 올바르지 않습니다.", LogLevel.INFO),
    NOT_FOUND_DATA(HttpStatus.NOT_FOUND, "해당 데이터를 찾을 수 없습니다.", LogLevel.INFO),

    // Auth
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.", LogLevel.INFO),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다.", LogLevel.INFO),

    // Couple
    INVITE_STATE_INVALID(HttpStatus.BAD_REQUEST, "초대 상태가 올바르지 않습니다.", LogLevel.INFO),
    INVALID_INVITE_KEY(HttpStatus.BAD_REQUEST, "유효하지 않은 초대 코드입니다.", LogLevel.INFO),
    ALREADY_JOINED_COUPLE(HttpStatus.BAD_REQUEST, "이미 다른 커플에 참여 중입니다.", LogLevel.INFO),
    COUPLE_OPERATION_NOW_ALLOWED(HttpStatus.BAD_REQUEST, "", LogLevel.INFO),

    RECORD_IMAGE_INVALID(HttpStatus.BAD_REQUEST, "업로드 할 수 없는 사진입니다.", LogLevel.INFO),
    RECORD_BAD_IMAGE(HttpStatus.BAD_REQUEST, "사용할 수 없는 이미지입니다.", LogLevel.INFO),
}
