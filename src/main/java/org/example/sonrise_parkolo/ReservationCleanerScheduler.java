package org.example.sonrise_parkolo;

import lombok.RequiredArgsConstructor;
import org.example.sonrise_parkolo.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReservationCleanerScheduler {

    private final ReservationRepository reservationRepository;

    @Scheduled(cron = "0 * * * * *")
    public void cleanupExpiredReservations() {
        reservationRepository.deleteByEndTimeBefore(LocalDateTime.now());
    }
}
