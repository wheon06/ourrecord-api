package com.wheon.ourrecord.api.config

import org.slf4j.MDC
import org.springframework.stereotype.Component

@Component
class MDCHandlerCommentAppender(
    private val handlerKeys: Set<String> = HANDLER_MDC_KEYS,
) {
    companion object {
        private val HANDLER_MDC_KEYS = setOf("handler")
    }

    init {
        require(handlerKeys.isNotEmpty()) { "handlerKeys는 null 혹은 empty 이면 안됩니다." }
    }

    fun appendHandlerName(sql: String): String {
        val handlerMdcValue = readHandlerMdcValue() ?: return sql
        return buildSqlWithHandlerInfo(sql, handlerMdcValue)
    }

    private fun readHandlerMdcValue(): String? {
        return handlerKeys.firstNotNullOfOrNull { key ->
            MDC.get(key)?.takeIf { it.isNotBlank() }
        }
    }

    private fun buildSqlWithHandlerInfo(sql: String, handlerMdcValue: String): String {
        return "/* handler: $handlerMdcValue */ $sql"
    }
}
