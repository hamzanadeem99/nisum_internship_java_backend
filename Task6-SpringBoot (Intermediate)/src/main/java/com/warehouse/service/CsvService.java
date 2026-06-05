package com.warehouse.service;

import com.warehouse.entity.Product;
import com.warehouse.repository.ProductRepository;
import com.warehouse.util.CsvUtil;
import com.warehouse.util.ValidationUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class CsvService {

    private final ProductRepository productRepository;

    public CsvService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String processCSV(String filePath) {
        List<Product> allProducts;

        try {
            // Use getInputStream() directly. This works inside JARs too.
            ClassPathResource resource = new ClassPathResource("products.csv");
            try (InputStream inputStream = resource.getInputStream()) {
                allProducts = CsvUtil.readCSV(inputStream);
            }
        } catch (Exception e) {
            return "Error: Could not read products.csv from resources. " + e.getMessage();
        }

        List<Product> validProducts = new CopyOnWriteArrayList<>();
        List<String> defectiveList = new CopyOnWriteArrayList<>();
        List<String> errorList = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<?>> futures = new ArrayList<>();

        for (Product product : allProducts) {
            futures.add(executor.submit(() -> {
                try {
                    // Changed to product.isDefective() to match typical Lombok naming
                    if (product.isDefective()) {
                        defectiveList.add("Defective: " + (product.getName() != null ? product.getName() : "Unknown"));
                        return;
                    }
                    ValidationUtil.validate(product);
                    validProducts.add(product);
                } catch (Exception e) {
                    errorList.add("Error for " + (product.getName() != null ? product.getName() : "Unknown") + ": " + e.getMessage());
                }
            }));
        }

        // Wait for results
        for (Future<?> future : futures) {
            try { future.get(); } catch (Exception e) { errorList.add("Thread error: " + e.getMessage()); }
        }
        executor.shutdown();

        if (!validProducts.isEmpty()) {
            productRepository.saveAll(validProducts);
        }

        return buildSummary(allProducts.size(), validProducts.size(), defectiveList, errorList);
    }

    private String buildSummary(int total, int saved, List<String> defective, List<String> errors) {
        return "CSV Processing Complete\n" +
                "Total rows: " + total + "\n" +
                "Saved: " + saved + "\n" +
                "Defective: " + defective.size() + "\n" +
                "Errors: " + errors.size() + (defective.isEmpty() ? "" : "\n\nDefective:\n" + String.join("\n", defective));
    }
}
