package com.warehouse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "laptops")
@DiscriminatorValue("LAPTOP")
public class Laptop extends Product {

    @Column(name = "screenSize")
    private double screenSize;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ramAndStorage")
    private String ramAndStorage;

    public Laptop() {}

    public Laptop(String productId, String name, String brand, double price, int quantity, int warrantyYears, double screenSize, String processor, String ramAndStorage) {
        super(productId, name, brand, price, quantity, warrantyYears);
        this.screenSize = screenSize;
        this.processor = processor;
        this.ramAndStorage = ramAndStorage;
    }

    // Getters
    public double getScreenSize() {
        return screenSize;
    }

    public String getProcessor() {
        return processor;
    }

    public String getRamAndStorage() {
        return ramAndStorage;
    }

    // Setters
    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRamAndStorage(String ramAndStorage) {
        this.ramAndStorage = ramAndStorage;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nScreen: %s\" | CPU: %s | RAM/Storage: %s", screenSize, processor, ramAndStorage);
    }
}
