package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import org.springframework.stereotype.Component

@Component
class CoupleFinder(
    private val coupleMemberRepository: CoupleMemberRepository,
    private val coupleReader: CoupleReader,
)
