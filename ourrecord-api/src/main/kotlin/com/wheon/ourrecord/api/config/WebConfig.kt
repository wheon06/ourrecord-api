package com.wheon.ourrecord.api.config

import com.wheon.ourrecord.support.auth.ApiUserArgumentResolver
import com.wheon.ourrecord.support.auth.token.TokenManager
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val tokenManager: TokenManager,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(ApiUserArgumentResolver(tokenManager))
    }
}
