package com.warehouse.csv;

import com.warehouse.dao.*;
import com.warehouse.model.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class CSVProcessor {

    private final LaptopDAO laptopDAO = new LaptopDAO();
    private final PhoneDAO phoneDAO = new PhoneDAO();
    private final AccessoryDAO accessoryDAO = new AccessoryDAO();

    // Threadsafe lists
    private final List<Product> validProducts =
            Collections.synchronizedList(new ArrayList<>());
    private final List<String[]> defectiveProducts =
            Collections.synchronizedList(new ArrayList<>());
    private final List<String> errorRecords =
            Collections.synchronizedList(new ArrayList<>());

    public void processCSV(String filePath) {
        System.out.println("\nProcessing CSV: " + filePath);

        List<String[]> allRows = readCSV(filePath);
        if (allRows.isEmpty()) {
            System.out.println("No data found in CSV!");
            return;
        }

        System.out.println("Total rows read: " + allRows.size());

        // Split into chunks for threading
        int chunkSize = Math.max(1, allRows.size() / 3);
        List<List<String[]>> chunks = splitIntoChunks(allRows, chunkSize);

        // Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(chunks.size());

        // Submit each chunk to thread
        List<Future<?>> futures = new ArrayList<>();
        for (List<String[]> chunk : chunks) {
            Future<?> future = executor.submit(() -> processChunk(chunk));
            futures.add(future);
        }

        // Wait for all threads to finish
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                System.err.println("Thread error: " + e.getMessage());
            }
        }

        executor.shutdown();

        // Save valid products to DB
        saveValidProducts();

        // Print summary
        printSummary();
    }

    private List<String[]> readCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }
                if (!line.trim().isEmpty()) {
                    rows.add(line.split(",", -1));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
        return rows;
    }

    private void processChunk(List<String[]> chunk) {
        for (String[] fields : chunk) {
            processRow(fields);
        }
    }

    private void processRow(String[] fields) {
        try {
            // Check isDefective column (index 15)
            if (fields.length < 16) {
                errorRecords.add("Invalid row: insufficient fields");
                return;
            }

            String isDefective = fields[15].trim().toLowerCase();

            if (isDefective.equals("true")) {
                // Add to defective list
                defectiveProducts.add(fields);
                return;
            }

            // Validate fields
            if (!CSVValidator.isValid(fields)) {
                String error = CSVValidator.getValidationError(fields);
                errorRecords.add("Invalid record [" + fields[0] + "]: " + error);
                return;
            }

            // Parse and add to valid list
            Product product = parseProduct(fields);
            if (product != null) {
                validProducts.add(product);
            }

        } catch (Exception e) {
            errorRecords.add("Error processing row: " + e.getMessage());
        }
    }

    private Product parseProduct(String[] f) {
        try {
            String productId = f[0].trim();
            String name = f[1].trim();
            String brand = f[2].trim();
            double price = Double.parseDouble(f[3].trim());
            int quantity = Integer.parseInt(f[4].trim());
            int warrantyYears = Integer.parseInt(f[5].trim());
            String productType = f[6].trim();

            switch (productType) {
                case "LAPTOP":
                    double screenSize = f[7].trim().isEmpty()
                            ? 0 : Double.parseDouble(f[7].trim());
                    String processor = f[8].trim();
                    String ramStorage = f[9].trim();
                    return new Laptop(productId, name, brand, price, quantity, warrantyYears, screenSize, processor, ramStorage);

                case "PHONE":
                    double pScreen = f[7].trim().isEmpty()
                            ? 0 : Double.parseDouble(f[7].trim());
                    int battery = f[10].trim().isEmpty()
                            ? 0 : Integer.parseInt(f[10].trim());
                    int camera  = f[11].trim().isEmpty()
                            ? 0 : Integer.parseInt(f[11].trim());
                    return new Phone(productId, name, brand, price, quantity, warrantyYears, pScreen, battery, camera);

                case "ACCESSORY":
                    String connector = f[12].trim();
                    int ports = f[13].trim().isEmpty()
                            ? 0 : Integer.parseInt(f[13].trim());
                    String compatible = f[14].trim();
                    return new Accessory(productId, name, brand, price, quantity, warrantyYears, connector, ports, compatible);

                default:
                    return null;
            }
        } catch (Exception e) {
            errorRecords.add("Parse error: " + e.getMessage());
            return null;
        }
    }

    private void saveValidProducts() {
        System.out.println("\nSaving valid products");
        for (Product p : validProducts) {
            if (p instanceof Laptop)
                laptopDAO.save((Laptop) p);
            else if (p instanceof Phone)
                phoneDAO.save((Phone) p);
            else if (p instanceof Accessory)
                accessoryDAO.save((Accessory) p);
        }
    }

    private List<List<String[]>> splitIntoChunks(
            List<String[]> list, int chunkSize) {
        List<List<String[]>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size();
             i += chunkSize) {chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }

    private void printSummary() {
        System.out.println("CSV PROCESSING SUMMARY");
        System.out.println("\nValid products saved: " + validProducts.size());
        System.out.println("\nDefective products: " + defectiveProducts.size());
        System.out.println("\nError records: " + errorRecords.size());

        if (!defectiveProducts.isEmpty()) {
            System.out.println("\nDefective Products:");
            for (String[] d : defectiveProducts) {
                System.out.println("  -> " + d[0] + " | " + d[1]);
            }
        }

        if (!errorRecords.isEmpty()) {
            System.out.println("\nErrors:");
            for (String e : errorRecords) {
                System.out.println("Failed" + e);
            }
        }
    }

    public List<Product> getValidProducts() {
        return validProducts;
    }

    public List<String[]> getDefectiveProducts() {
        return defectiveProducts;
    }
}
