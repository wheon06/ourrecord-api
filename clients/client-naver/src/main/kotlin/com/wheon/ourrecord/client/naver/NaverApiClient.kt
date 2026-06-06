package com.wheon.ourrecord.client.naver

import com.wheon.ourrecord.client.naver.model.NaverClientPlaceResult
import org.springframework.stereotype.Component

@Component
class NaverApiClient internal constructor(
    private val naverApi: NaverApi,
) {
    fun searchPlace(
        keyword: String,
    ): NaverClientPlaceResult {
        return naverApi.searchPlace(keyword).toResult()
    }
}
