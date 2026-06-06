package com.wheon.ourrecord.storage.db.core.seeder

import com.wheon.ourrecord.storage.db.core.PlaceCategoryEntity
import com.wheon.ourrecord.storage.db.core.PlaceCategoryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class PlaceCategorySeeder(
    private val placeCategoryRepository: PlaceCategoryRepository,
) : CommandLineRunner {
    override fun run(vararg args: String) {
        val entities = placeCategoryRepository.findAll()
        if (entities.isEmpty()) {
            placeCategoryRepository.saveAll(
                listOf(
                    PlaceCategoryEntity(
                        code = "CAFE",
                        "카페",
                        "☕",
                        1,
                    ),
                    PlaceCategoryEntity(
                        code = "RESTAURANT",
                        "맛집",
                        "🍚",
                        2,
                    ),
                    PlaceCategoryEntity(
                        code = "TRAVEL",
                        "여행",
                        "✈️",
                        3,
                    ),
                    PlaceCategoryEntity(
                        code = "CLIMBING",
                        "클라이밍",
                        "🧗",
                        4,
                    ),
                    PlaceCategoryEntity(
                        code = "MOVIE",
                        "영화관",
                        "🎬",
                        5,
                    ),
                ),
            )
        }
    }
}
