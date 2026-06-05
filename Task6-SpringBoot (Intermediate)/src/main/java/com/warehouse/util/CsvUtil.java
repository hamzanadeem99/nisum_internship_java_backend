package com.warehouse.util;

import com.warehouse.entity.Product;
import com.warehouse.enums.ProductType;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<Product> readCSV(InputStream inputStream) {
        List<Product> products = new ArrayList<>();
        System.out.println("DEBUG: Starting readCSV method");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                // Log the raw line to see what Java is reading
                System.out.println("DEBUG: Row found -> " + line);

                if (firstLine) {
                    System.out.println("DEBUG: Skipping Header Row");
                    firstLine = false;
                    continue;
                }

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(",", -1);
                System.out.println("DEBUG: Columns detected -> " + data.length);

                // Relaxed check. As long as we have core data (first 7 columns), we proceed.
                if (data.length < 7) {
                    System.out.println("DEBUG: Skipping - Row too short even for basic data.");
                    continue;
                }

                try {
                    // Using getSafe to avoid ArrayIndexOutOfBoundsException
                    Product product = Product.builder()
                            .name(getSafe(data, 0)) // Note: if CSV starts with Name at index 0, adjust accordingly
                            .brand(getSafe(data, 1))
                            .price(parsePrice(getSafe(data, 2)))
                            .quantity(parseQuantity(getSafe(data, 3)))
                            .warrantyYears(parseQuantity(getSafe(data, 4)))
                            .productType(parseProductType(getSafe(data, 5)))
                            .screenSize(getSafe(data, 6))
                            .processor(getSafe(data, 7))
                            .ramAndStorage(getSafe(data, 8))
                            .batteryCapacity(getSafe(data, 9))
                            .cameraMP(getSafe(data, 10))
                            .connectorType(getSafe(data, 11))
                            .portCount(parseQuantity(getSafe(data, 12)))
                            .compatibleWith(getSafe(data, 13))
                            // DYNAMIC INDEX: Takes the very last value in the array as the boolean
                            .defective(Boolean.parseBoolean(data[data.length - 1].trim()))
                            .build();

                    products.add(product);
                    System.out.println("DEBUG: Successfully parsed: " + product.getName());
                } catch (Exception e) {
                    System.out.println("DEBUG: Row error during building: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("DEBUG: Fatal CSV Read Error: " + e.getMessage());
        }
        System.out.println("DEBUG: Finished. Total parsed: " + products.size());
        return products;
    }

    // Safety Helpers

    private static String getSafe(String[] data, int index) {
        return (index < data.length) ? data[index].trim() : "";
    }

    private static double parsePrice(String input) {
        try { return Double.parseDouble(input.replaceAll("[^\\d.]", "")); }
        catch (Exception e) { return 0.0; }
    }

    private static int parseQuantity(String input) {
        try { return Integer.parseInt(input.replaceAll("[^\\d]", "")); }
        catch (Exception e) { return 0; }
    }

    private static ProductType parseProductType(String input) {
        try { return ProductType.valueOf(input.toUpperCase().trim()); }
        catch (Exception e) { return ProductType.LAPTOP; } // Default fallback
    }
}
