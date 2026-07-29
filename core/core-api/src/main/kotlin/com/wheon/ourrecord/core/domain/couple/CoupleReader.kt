package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.storage.db.core.MemberRepository
import com.wheon.ourrecord.storage.db.core.SpaceRepository
import org.springframework.stereotype.Component

@Component
class CoupleReader(
    private val spaceRepository: SpaceRepository,
    private val memberRepository: MemberRepository,
)
