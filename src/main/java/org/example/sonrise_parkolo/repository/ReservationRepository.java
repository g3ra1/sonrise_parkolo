package org.example.sonrise_parkolo.repository;

import jakarta.transaction.Transactional;
import org.example.sonrise_parkolo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByParkingSpotIdAndKezdoIdoBeforeAndVegsoIdoAfter(
            Long parkingSpotId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );

    Optional<Reservation> findByCancellationCode(String cancellationCode);

    @Transactional
    void deleteByVegsoIdoBefore(LocalDateTime now);
}
