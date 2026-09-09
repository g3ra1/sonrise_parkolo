package org.sonrise_parkolo.repository;

import jakarta.transaction.Transactional;
import org.sonrise_parkolo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByParkingSpotIdAndStartTimeBeforeAndEndTimeAfter(
            Long parkingSpotId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );

    Optional<Reservation> findByParkingCode(String parkingCode);

    boolean existsByParkingCode(String parkingCode);

    void deleteByParkingCode(String parkingCode);

    @Transactional
    void deleteByEndTimeBefore(LocalDateTime now);
}
