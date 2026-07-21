package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import com.wheon.ourrecord.storage.db.core.CoupleRepository
import org.springframework.stereotype.Component

@Component
class CoupleReader(
    private val coupleRepository: CoupleRepository,
    private val coupleMemberRepository: CoupleMemberRepository,
)
