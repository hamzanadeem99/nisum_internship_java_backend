package com.warehouse.dto;

import com.warehouse.enums.ProductType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private int id;
    private String productId;
    private String name;
    private String brand;
    private double price;
    private int quantity;
    private int warrantyYears;

    private ProductType productType;

    private String screenSize;
    private String processor;
    private String ramAndStorage;
    private String batteryCapacity;
    private String cameraMP;
    private String connectorType;
    private int portCount;
    private String compatibleWith;

    private boolean defective;
}
