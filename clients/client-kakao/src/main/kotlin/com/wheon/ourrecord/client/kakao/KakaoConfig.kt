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
    @param:Value($$"${kakao-api.core.base-url}") private val kakaoCoreApiBaseUrl: String,
    @param:Value($$"${kakao-api.auth.base-url}") private val kakaoAuthApiBaseUrl: String,
    @param:Value($$"${kakao-api.map.base-url}") private val kakaoMapApiBaseUrl: String,
    @param:Value($$"${kakao-api.client-id}") private val kakaoClientId: String,
) {
    @Bean
    fun kakaoCoreApi(): KakaoCoreApi {
        val client = RestClient.builder()
            .baseUrl(kakaoCoreApiBaseUrl)
            .defaultHeaders { headers ->
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            }
            .build()

        val adapter = RestClientAdapter.create(client)
        val factory = HttpServiceProxyFactory.builder().exchangeAdapter(adapter).build()
        return factory.createClient<KakaoCoreApi>()
    }

    @Bean
    fun kakaoAuthApi(): KakaoAuthApi {
        val client = RestClient.builder()
            .baseUrl(kakaoAuthApiBaseUrl)
            .defaultHeaders { headers ->
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            }
            .build()

        val adapter = RestClientAdapter.create(client)
        val factory = HttpServiceProxyFactory.builder().exchangeAdapter(adapter).build()
        return factory.createClient<KakaoAuthApi>()
    }

    @Bean
    fun kakaoMapApi(): KakaoMapApi {
        val client = RestClient.builder()
            .baseUrl(kakaoMapApiBaseUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK $kakaoClientId")
            .build()

        val adapter = RestClientAdapter.create(client)
        val factory = HttpServiceProxyFactory.builder().exchangeAdapter(adapter).build()
        return factory.createClient<KakaoMapApi>()
    }
}
