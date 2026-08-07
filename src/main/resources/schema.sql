CREATE TABLE IF NOT EXISTS parking_spot (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            occupied BOOLEAN NOT NULL DEFAULT FALSE
);