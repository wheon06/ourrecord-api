package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.enums.SpaceInviteState

data class InviteResponse(
    val inviteKey: String,
    val state: SpaceInviteState,
)
