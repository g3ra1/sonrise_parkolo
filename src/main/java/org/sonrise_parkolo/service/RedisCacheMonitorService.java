package org.sonrise_parkolo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCacheMonitorService {

    private final StringRedisTemplate redisTemplate;;

    @Scheduled(cron = "0 * * * * *")
    public void inspectRedisCache() {
        log.info("--- [Redis Cache Tartalom Ellenőrzése] ---");

        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match("*")
                .count(100)
                .build();

        try (Cursor<String> cursor = redisTemplate.scan(scanOptions)) {
            int count = 0;
            while (cursor.hasNext()) {
                String key = cursor.next();
                Object value = redisTemplate.opsForValue().get(key);
                Long expire = redisTemplate.getExpire(key);

                log.info("Kulcs: [{}], Érték: [{}], TTL: {} mp", key, value, expire);
                count++;
            }

            if (count == 0) {
                log.info("A Redis cache jelenleg üres.");
            } else {
                log.info("Összesen {} elem található a Redisben.", count);
            }
        } catch (Exception e) {
            log.error("Hiba történt a Redis cache lekérdezése közben: {}", e.getMessage());
        }
    }
}