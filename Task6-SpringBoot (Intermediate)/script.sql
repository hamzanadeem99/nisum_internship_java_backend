-- 1. Setup
CREATE DATABASE IF NOT EXISTS electronics_warehouse_db;
USE electronics_warehouse_db;

-- 2. Cleanup
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS `user`; -- Use backticks because 'user' is a reserved keyword

-- 3. Create Product Table (Professional Auto-Increment Approach)
CREATE TABLE product (
    id               INT NOT NULL AUTO_INCREMENT, -- Primary Key for DB logic
    name             VARCHAR(100) NOT NULL,
    brand            VARCHAR(100) NOT NULL,
    price            DOUBLE DEFAULT 0.0,
    quantity         INT DEFAULT 0,
    product_type     VARCHAR(30) NOT NULL,
    warranty_years   INT DEFAULT 0,
    screen_size      VARCHAR(50),
    processor        VARCHAR(100),
    ram_and_storage  VARCHAR(100),
    battery_capacity VARCHAR(50),
    camera_mp        VARCHAR(50),
    connector_type   VARCHAR(50),
    port_count       INT DEFAULT 0,
    compatible_with  VARCHAR(255),
    is_defective     BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);

-- 4. Create User Table (Updated to match Java Entity with numeric ID)
CREATE TABLE `user` (
    id               INT NOT NULL AUTO_INCREMENT, -- Added to match Java @Id
    username         VARCHAR(50) NOT NULL UNIQUE, -- Keep unique for login
    password         VARCHAR(100) NOT NULL,
    role             VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

-- 5. Seed Data (Notice we don't insert 'id' manually; MySQL handles it)
INSERT INTO `user` (username, password, role) VALUES
    ('admin', 'admin123', 'ADMIN'),
    ('user', 'user123', 'USER');

-- 6. Verification
SELECT * FROM user;
SELECT * FROM product;
