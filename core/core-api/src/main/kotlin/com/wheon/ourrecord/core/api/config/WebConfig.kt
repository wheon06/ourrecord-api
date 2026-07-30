package com.wheon.ourrecord.core.api.config

import com.wheon.ourrecord.core.support.auth.CoupleUserArgumentResolver
import com.wheon.ourrecord.core.support.auth.UserArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val userArgumentResolver: UserArgumentResolver,
    private val coupleUserArgumentResolver: CoupleUserArgumentResolver,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userArgumentResolver)
        resolvers.add(coupleUserArgumentResolver)
    }
}
