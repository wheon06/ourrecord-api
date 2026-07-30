package com.wheon.ourrecord.core.api.controller

import com.wheon.ourrecord.core.api.assembler.SpaceAssembler
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ViewController(
    private val spaceAssembler: SpaceAssembler,
) {
    @GetMapping("/invite/{inviteKey}", produces = [MediaType.TEXT_HTML_VALUE])
    fun landing(@PathVariable inviteKey: String): String {
        val response = spaceAssembler.getInvite(inviteKey)
        val template = ClassPathResource("templates/invite-landing.html").inputStream.readBytes().decodeToString()
        return template
            .replace("{{INVITER_NAME}}", response.inviterName)
            .replace("{{INVITE_CODE}}", response.inviteKey)
    }
}
