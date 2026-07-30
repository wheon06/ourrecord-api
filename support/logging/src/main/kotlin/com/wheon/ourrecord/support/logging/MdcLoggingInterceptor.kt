package com.wheon.ourrecord.support.logging

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import java.util.UUID

class MdcLoggingInterceptor : HandlerInterceptor {
    companion object {
        private const val REQUEST_ID_MDC_KEY = "traceId"
        private const val REQUEST_CONTROLLER_MDC_KEY = "handler"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            val handlerName = handler.beanType.simpleName
            val methodName = handler.method.name
            val controllerInfo = "$handlerName.$methodName"
            MDC.put(REQUEST_CONTROLLER_MDC_KEY, controllerInfo)
            MDC.put(REQUEST_ID_MDC_KEY, UUID.randomUUID().toString())
        }
        return true
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        MDC.clear()
    }
}
