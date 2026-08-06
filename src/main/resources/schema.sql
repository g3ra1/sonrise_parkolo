CREATE TABLE parking_spot (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              spot_number BIGINT NOT NULL,
                              foglalt BOOLEAN DEFAULT FALSE
);

CREATE TABLE reservation (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             parking_spot_id BIGINT NOT NULL,
                             start_time DATETIME NOT NULL,
                             end_time DATETIME NOT NULL,
                             cancellation_code VARCHAR(255)
                             CONSTRAINT fk_reservation_parking_spot
                                 FOREIGN KEY (parking_spot_id)
                                     REFERENCES parking_spot(id)
                                     ON DELETE CASCADE
)
)