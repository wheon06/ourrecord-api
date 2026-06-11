package com.wheon.ourrecord.domain.place

import com.wheon.ourrecord.core.enums.CouplePlaceVisibility
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CouplePlaceEntity
import com.wheon.ourrecord.storage.db.core.CouplePlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceEntity
import com.wheon.ourrecord.storage.db.core.PlaceRepository
import com.wheon.ourrecord.storage.db.core.PlaceSourceRefEntity
import com.wheon.ourrecord.storage.db.core.PlaceSourceRefRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CouplePlaceManager(
    private val placeRepository: PlaceRepository,
    private val placeSourceRefRepository: PlaceSourceRefRepository,
    private val couplePlaceRepository: CouplePlaceRepository,
) {
    @Transactional
    fun findOrCreate(coupleId: Long, memberId: Long, place: AddPlace): Long {
        val placeId = findOrCreatePlace(place)

        val existing = couplePlaceRepository.findByCoupleIdAndPlaceId(
            coupleId = coupleId,
            placeId = placeId,
        )

        if (existing != null) {
            if (existing.isDeleted()) existing.active()
            existing.changeCategory(place.categoryCode)
            return existing.id
        }

        return couplePlaceRepository.save(
            CouplePlaceEntity(
                coupleId = coupleId,
                placeId = placeId,
                categoryCode = place.categoryCode,
                savedByMemberId = memberId,
                visibility = CouplePlaceVisibility.SAVED,
            ),
        ).id
    }

    @Transactional
    fun update(coupleId: Long, couplePlaceId: Long, categoryCode: String?) {
        val couplePlace = getActiveCouplePlace(
            coupleId = coupleId,
            couplePlaceId = couplePlaceId,
        )

        couplePlace.changeCategory(categoryCode)
    }

    @Transactional
    fun delete(coupleId: Long, couplePlaceId: Long) {
        val couplePlace = getActiveCouplePlace(
            coupleId = coupleId,
            couplePlaceId = couplePlaceId,
        )

        couplePlace.delete()
    }

    private fun findOrCreatePlace(place: AddPlace): Long {
        val existingRef = placeSourceRefRepository.findBySourceAndExternalPlaceId(
            source = place.source,
            externalPlaceId = place.externalPlaceId,
        )

        if (existingRef != null) {
            return existingRef.placeId
        }

        val savedPlace = placeRepository.save(
            PlaceEntity(
                name = place.name,
                address = place.address,
                roadAddress = place.roadAddress,
                latitude = place.latitude,
                longitude = place.longitude,
                geoHash = "",
            ),
        )

        placeSourceRefRepository.save(
            PlaceSourceRefEntity(
                placeId = savedPlace.id,
                source = place.source,
                externalPlaceId = place.externalPlaceId,
                providerName = place.name,
                providerCategory = place.providerCategory,
                rawPayload = place.rawPayload,
            ),
        )

        return savedPlace.id
    }

    private fun getActiveCouplePlace(coupleId: Long, couplePlaceId: Long): CouplePlaceEntity {
        return couplePlaceRepository.findByIdAndCoupleIdAndStatus(
            id = couplePlaceId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
        ) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
    }
}
