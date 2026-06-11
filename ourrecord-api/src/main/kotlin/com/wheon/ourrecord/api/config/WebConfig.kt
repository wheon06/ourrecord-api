package com.wheon.ourrecord.api.config

import com.wheon.ourrecord.support.auth.ApiCoupleUserArgumentResolver
import com.wheon.ourrecord.support.auth.ApiUserArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val apiUserArgumentResolver: ApiUserArgumentResolver,
    private val apiCoupleUserArgumentResolver: ApiCoupleUserArgumentResolver,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(apiUserArgumentResolver)
        resolvers.add(apiCoupleUserArgumentResolver)
    }
}
