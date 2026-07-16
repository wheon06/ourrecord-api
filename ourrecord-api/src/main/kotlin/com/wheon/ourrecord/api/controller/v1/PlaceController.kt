package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.client.kakao.KakaoMapClient
import com.wheon.ourrecord.domain.place.PlaceService
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController(
    private val placeService: PlaceService,
    private val kakaoMapClient: KakaoMapClient,
)
