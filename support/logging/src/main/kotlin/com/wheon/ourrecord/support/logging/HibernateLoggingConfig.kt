package com.wheon.ourrecord.support.logging

import org.hibernate.cfg.JdbcSettings
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HibernateLoggingConfig {
    @Bean
    fun statementInspectorCustomizer(
        inspector: MDCRequestHandlerCommentInspector,
    ): HibernatePropertiesCustomizer {
        return HibernatePropertiesCustomizer { properties ->
            properties[JdbcSettings.STATEMENT_INSPECTOR] = inspector
        }
    }
}
