package org.example.sonrise_parkolo.repository;

import org.example.sonrise_parkolo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
