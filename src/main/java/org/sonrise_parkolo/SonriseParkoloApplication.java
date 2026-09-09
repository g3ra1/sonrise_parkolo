package org.sonrise_parkolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SonriseParkoloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SonriseParkoloApplication.class, args);
    }

}
