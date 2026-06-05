package com.warehouse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phones")
@DiscriminatorValue("PHONE")
public class Phone extends Product {

    @Column(name = "screenSize")
    private double screenSize;

    @Column(name = "batteryCapacity")
    private int batteryCapacity;

    @Column(name = "cameraMP")
    private int cameraMP;

    public Phone() {}

    public Phone(String productId, String name, String brand, double price, int quantity, int warrantyYears, double screenSize, int batteryCapacity, int cameraMP) {
        super(productId, name, brand, price, quantity, warrantyYears);
        this.screenSize = screenSize;
        this.batteryCapacity = batteryCapacity;
        this.cameraMP = cameraMP;
    }

    // Getters
    public double getScreenSize() {
        return screenSize;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public int getCameraMP() {
        return cameraMP;
    }

    // Setters
    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setCameraMP(int cameraMP) {
        this.cameraMP = cameraMP;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nScreen: %s\" | Battery: %dmAh | Camera: %dMP", screenSize, batteryCapacity, cameraMP);
    }
}
