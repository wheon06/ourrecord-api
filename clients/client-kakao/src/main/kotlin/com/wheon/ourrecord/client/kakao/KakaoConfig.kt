package com.wheon.ourrecord.client.kakao

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient

@Configuration
internal class KakaoConfig(
    @param:Value($$"${kakao.base-url}") private val baseUrl: String,
) {
    @Bean
    fun kakaoApi(): KakaoApi {
        val client = RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeaders { headers ->
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            }
            .build()

        val adapter = RestClientAdapter.create(client)
        val factory = HttpServiceProxyFactory.builder().exchangeAdapter(adapter).build()
        return factory.createClient<KakaoApi>()
    }
}
