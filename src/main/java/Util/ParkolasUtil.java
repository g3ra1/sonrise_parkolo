package Util;

import lombok.AllArgsConstructor;
import org.sonrise_parkolo.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@AllArgsConstructor
public class ParkolasUtil {

    private ParkolasUtil() {}

    private ReservationRepository reservationRepository;

    public String parkoloKodGenerator() {
        String KARAKTER_KESZLET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        String parkoloKod;

        do {
            StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                sb.append(KARAKTER_KESZLET.charAt(random.nextInt(KARAKTER_KESZLET.length())));
            }
            parkoloKod = sb.toString();
        } while (reservationRepository.existsByParkingCode(parkoloKod));

        return parkoloKod;
    }

}
