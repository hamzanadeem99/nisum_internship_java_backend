package com.warehouse.util;

import com.warehouse.entity.Product;

public class ValidationUtil {

    public static void validate(Product product) {

        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (product.getBrand() == null || product.getBrand().isBlank()) {
            throw new IllegalArgumentException("Brand is required");
        }

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + product.getPrice());
        }

        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative: " + product.getQuantity());
        }

        if (product.getProductType() == null) {
            throw new IllegalArgumentException("Product type is required");
        }
    }
}
