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
    @param:Value($$"${kakao-auth.base-url}") private val kakaoAuthBaseUrl: String,
    @param:Value($$"${kakao-api.base-url}") private val kakaoApiBaseUrl: String,
) {
    @Bean
    fun kakaoAuthApi(): KakaoAuthApi {
        val client = RestClient.builder()
            .baseUrl(kakaoAuthBaseUrl)
            .defaultHeaders { headers ->
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            }
            .build()

        val adapter = RestClientAdapter.create(client)
        val factory = HttpServiceProxyFactory.builder().exchangeAdapter(adapter).build()
        return factory.createClient<KakaoAuthApi>()
    }

    @Bean
    fun kakaoApi(): KakaoApi {
        val client = RestClient.builder()
            .baseUrl(kakaoApiBaseUrl)
            .defaultHeaders { headers ->
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            }
            .build()

        val adapter = RestClientAdapter.create(client)
        val factory = HttpServiceProxyFactory.builder().exchangeAdapter(adapter).build()
        return factory.createClient<KakaoApi>()
    }
}
