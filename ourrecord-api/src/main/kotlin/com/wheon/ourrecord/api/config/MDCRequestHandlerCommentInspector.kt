package com.wheon.ourrecord.api.config

import org.hibernate.resource.jdbc.spi.StatementInspector
import org.springframework.stereotype.Component

@Component
class MDCRequestHandlerCommentInspector(
    private val mdcRequestHandlerCommentAppender: MDCHandlerCommentAppender
) : StatementInspector {
    override fun inspect(sql: String): String {
        return mdcRequestHandlerCommentAppender.appendHandlerName(sql)
    }

}
