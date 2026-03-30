CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     email VARCHAR(100) UNIQUE NOT NULL,
                                     username VARCHAR(50) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     user_type ENUM('GUEST', 'ADMINISTRATOR') NOT NULL
);

CREATE TABLE IF NOT EXISTS hotels (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      code VARCHAR(50) UNIQUE NOT NULL,
                                      room_quantity INT NOT NULL,
                                      administrator_id BIGINT,
                                      FOREIGN KEY (administrator_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS rooms (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     code VARCHAR(50) UNIQUE NOT NULL,
                                     size VARCHAR(50) NOT NULL,
                                     person_quantity INT NOT NULL,
                                     state ENUM('AVAILABLE', 'OCCUPIED') NOT NULL,
                                     hotel_id BIGINT NOT NULL,
                                     FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reservations (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            code VARCHAR(50) UNIQUE NOT NULL,
                                            reservation_date DATE NOT NULL,
                                            user_id BIGINT NOT NULL,
                                            room_id BIGINT NOT NULL,
                                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                            FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS data_recovery (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             reservation_id BIGINT NOT NULL,
                                             user_id BIGINT NOT NULL,
                                             occupation_date DATE NOT NULL,
                                             FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON DELETE CASCADE,
                                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);