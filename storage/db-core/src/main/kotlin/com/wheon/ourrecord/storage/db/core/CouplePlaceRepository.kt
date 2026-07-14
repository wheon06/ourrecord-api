package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CouplePlaceVisibility
import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CouplePlaceRepository : JpaRepository<CouplePlaceEntity, Long> {
    fun findByCoupleIdAndPlaceId(coupleId: Long, placeId: Long): CouplePlaceEntity?
    fun findByIdAndCoupleIdAndStatus(id: Long, coupleId: Long, status: EntityStatus): CouplePlaceEntity?

    @Query(
        """
        SELECT new com.wheon.ourrecord.storage.db.core.CouplePlaceMapMarkerRow(
            couplePlace.id,
            place.id,
            couplePlace.categoryCode,
            place.name,
            place.address,
            place.roadAddress,
            place.latitude,
            place.longitude,
            COUNT(recordEntity.id),
            MAX(recordEntity.visitedOn)
        )
        FROM CouplePlaceEntity couplePlace
        JOIN PlaceEntity place
            ON place.id = couplePlace.placeId
            AND place.status = :status
        LEFT JOIN RecordEntity recordEntity
            ON recordEntity.couplePlaceId = couplePlace.id
            AND recordEntity.coupleId = couplePlace.coupleId
            AND recordEntity.status = :status
            AND recordEntity.state = :recordState
        WHERE couplePlace.coupleId = :coupleId
            AND couplePlace.status = :status
            AND couplePlace.visibility = :visibility
        GROUP BY
            couplePlace.id,
            place.id,
            couplePlace.categoryCode,
            place.name,
            place.address,
            place.roadAddress,
            place.latitude,
            place.longitude
        ORDER BY MAX(recordEntity.visitedOn) DESC, couplePlace.id DESC
        """,
    )
    fun findMapMarkersByCoupleId(
        coupleId: Long,
        status: EntityStatus,
        visibility: CouplePlaceVisibility,
        recordState: RecordState,
    ): List<CouplePlaceMapMarkerRow>
}
