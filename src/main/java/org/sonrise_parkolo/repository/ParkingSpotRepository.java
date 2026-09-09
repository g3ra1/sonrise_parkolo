package org.sonrise_parkolo.repository;


import org.sonrise_parkolo.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    Optional<ParkingSpot> findFirstByOccupiedFalseOrderByIdAsc();

}
