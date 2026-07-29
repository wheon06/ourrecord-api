package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.storage.db.core.MemberRepository
import org.springframework.stereotype.Component

@Component
class CoupleFinder(
    private val memberRepository: MemberRepository,
    private val coupleReader: CoupleReader,
)
