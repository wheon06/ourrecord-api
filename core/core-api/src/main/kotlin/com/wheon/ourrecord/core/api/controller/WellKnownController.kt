package com.wheon.ourrecord.core.api.controller

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WellKnownController {
    @GetMapping(
        "/.well-known/apple-app-site-association",
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun appleAppSiteAssociation(): Resource {
        return ClassPathResource("static/.well-known/apple-app-site-association")
    }
}
