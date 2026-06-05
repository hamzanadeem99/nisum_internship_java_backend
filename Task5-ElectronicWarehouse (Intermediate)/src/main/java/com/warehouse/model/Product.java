package com.warehouse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "productType", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {

    @Id
    @Column(name = "productId", length = 10)
    private String productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "warrantyYears")
    private int warrantyYears;

    @Column(name = "productType", insertable = false, updatable = false)
    private String productType;

    // No-arg constructor
    public Product() {}

    // Arg constructor
    public Product(String productId, String name, String brand, double price, int quantity, int warrantyYears) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.warrantyYears = warrantyYears;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    public String getProductType() {
        return productType;
    }

    // Setters
    public void setProductId(String id) {
        this.productId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWarrantyYears(int years) {
        this.warrantyYears = years;
    }

    @Override
    public String toString() {
        return String.format("ID: %-10s | Name: %-25s | Brand: %-10s | " + "Price: $%-8.2f | Quantity: %-5d | Warranty: %d yr",
                productId, name, brand, price, quantity, warrantyYears);
    }
}
