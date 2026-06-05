package com.warehouse.entity;

import com.warehouse.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // Internal Database Primary Key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "warranty_years")
    private int warrantyYears;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Column(name = "screen_size")
    private String screenSize;

    private String processor;

    @Column(name = "ram_and_storage")
    private String ramAndStorage;

    @Column(name = "battery_capacity")
    private String batteryCapacity;

    @Column(name = "camera_mp")
    private String cameraMP;

    @Column(name = "connector_type")
    private String connectorType;

    @Column(name = "port_count")
    private int portCount;

    @Column(name = "compatible_with")
    private String compatibleWith;

    @Column(name = "is_defective")
    private boolean defective = false;
}
