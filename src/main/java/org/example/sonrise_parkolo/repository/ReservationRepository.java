package org.example.sonrise_parkolo.repository;

import org.example.sonrise_parkolo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Reservation r " +
            "WHERE r.parkingSpotId = :spotId " +
            "AND (:kezdoIdo < r.vegsoIdo AND :endTime > r.kezdoIdo)")
    boolean existsOverlappingReservation(
            @Param("spotId") Long spotId,
            @Param("kezdoIdo") LocalDateTime kezdoIdo,
            @Param("kezdoIdo") LocalDateTime vegsoIdo);
}
