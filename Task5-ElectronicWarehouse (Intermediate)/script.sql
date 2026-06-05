-- Create database
CREATE DATABASE IF NOT EXISTS
    electronics_warehouse_db;

USE electronics_warehouse_db;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20) NOT NULL
    DEFAULT 'USER'
    );

-- Products base table
CREATE TABLE IF NOT EXISTS products (
    productId     VARCHAR(10) PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    brand         VARCHAR(50)  NOT NULL,
    price         DECIMAL(10,2) NOT NULL,
    quantity      INT NOT NULL DEFAULT 0,
    warrantyYears INT NOT NULL DEFAULT 0,
    productType   VARCHAR(20)  NOT NULL
    );

-- Laptops table
CREATE TABLE IF NOT EXISTS laptops (
    productId     VARCHAR(10) PRIMARY KEY,
    screenSize    DECIMAL(4,1),
    processor     VARCHAR(100),
    ramAndStorage VARCHAR(50),
    FOREIGN KEY (productId)
    REFERENCES products(productId)
    ON DELETE CASCADE
    );

-- Phones table
CREATE TABLE IF NOT EXISTS phones (
    productId       VARCHAR(10) PRIMARY KEY,
    screenSize      DECIMAL(4,1),
    batteryCapacity INT,
    cameraMP        INT,
    FOREIGN KEY (productId)
    REFERENCES products(productId)
    ON DELETE CASCADE
    );

-- Accessories table
CREATE TABLE IF NOT EXISTS accessories (
    productId      VARCHAR(10) PRIMARY KEY,
    connectorType  VARCHAR(50),
    portCount      INT,
    compatibleWith VARCHAR(100),
    FOREIGN KEY (productId)
    REFERENCES products(productId)
    ON DELETE CASCADE
    );

-- Insert default admin user
INSERT INTO users (username, password, role)
VALUES ('admin', 'admin123', 'ADMIN')
    ON DUPLICATE KEY UPDATE username = username;

INSERT INTO users (username, password, role)
VALUES ('user', 'user123', 'USER')
    ON DUPLICATE KEY UPDATE username = username;

SELECT 'Database setup complete' AS Status;
