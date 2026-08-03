package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Component

@Component
interface RecordModifyPostProcess {
    fun process(user: User, record: Record)
}
