package com.warehouse.mapper;

import com.warehouse.dto.ProductDTO;
import com.warehouse.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .brand(dto.getBrand())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .warrantyYears(dto.getWarrantyYears())
                .productType(dto.getProductType())
                .screenSize(dto.getScreenSize())
                .processor(dto.getProcessor())
                .ramAndStorage(dto.getRamAndStorage())
                .batteryCapacity(dto.getBatteryCapacity())
                .cameraMP(dto.getCameraMP())
                .connectorType(dto.getConnectorType())
                .portCount(dto.getPortCount())
                .compatibleWith(dto.getCompatibleWith())
                .defective(dto.isDefective())
                .build();
    }

    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .warrantyYears(product.getWarrantyYears())
                .productType(product.getProductType())
                .screenSize(product.getScreenSize())
                .processor(product.getProcessor())
                .ramAndStorage(product.getRamAndStorage())
                .batteryCapacity(product.getBatteryCapacity())
                .cameraMP(product.getCameraMP())
                .connectorType(product.getConnectorType())
                .portCount(product.getPortCount())
                .compatibleWith(product.getCompatibleWith())
                .defective(product.isDefective())
                .build();
    }
}
