package com.warehouse.repository;

import com.warehouse.entity.Product;
import com.warehouse.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Search by name (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Search by product type
    List<Product> findByProductType(ProductType productType);

    // Search by brand
    List<Product> findByBrandIgnoreCase(String brand);

    // Find non-defective only
    List<Product> findByDefectiveFalse();
}
