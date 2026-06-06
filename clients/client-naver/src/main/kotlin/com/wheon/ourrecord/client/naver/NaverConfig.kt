package com.wheon.ourrecord.client.naver

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient

@Configuration
internal class NaverConfig(
    @param:Value($$"${naver-api.base-url}") private val naverApiBaseUrl: String,
    @param:Value($$"${naver-api.client-id}") private val naverClientId: String,
    @param:Value($$"${naver-api.client-secret}") private val naverClientSecret: String,
) {
    @Bean
    fun naverApi(): NaverApi {
        val client = RestClient.builder()
            .baseUrl(naverApiBaseUrl)
            .defaultHeaders { headers ->
                headers.set("X-Naver-Client-Id", naverClientId)
                headers.set("X-Naver-Client-Secret", naverClientSecret)
            }
            .build()

        val adapter = RestClientAdapter.create(client)
        val factory = HttpServiceProxyFactory.builder().exchangeAdapter(adapter).build()
        return factory.createClient<NaverApi>()
    }
}
