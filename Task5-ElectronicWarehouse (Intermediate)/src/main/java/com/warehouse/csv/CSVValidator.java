package com.warehouse.csv;

public class CSVValidator {

    public static boolean isValid(String[] fields) {
        try {
            // Check minimum fields
            if (fields.length < 16) return false;

            // productId must not be empty
            String productId = fields[0].trim();
            if (productId.isEmpty()) return false;

            // name must not be empty
            String name = fields[1].trim();
            if (name.isEmpty()) return false;

            // brand must not be empty
            String brand = fields[2].trim();
            if (brand.isEmpty()) return false;

            // price must be positive
            double price = Double.parseDouble(fields[3].trim());
            if (price < 0) return false;

            // quantity must be non-negative
            int quantity = Integer.parseInt(fields[4].trim());
            if (quantity < 0) return false;

            // warrantyYears must be non-negative
            int warranty = Integer.parseInt(fields[5].trim());
            if (warranty < 0) return false;

            // productType must not be empty
            String type = fields[6].trim();
            if (type.isEmpty()) return false;
            if (!type.equals("LAPTOP") &&
                    !type.equals("PHONE") &&
                    !type.equals("ACCESSORY")) return false;

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getValidationError(String[] fields) {

        if (fields.length < 16)
            return "Insufficient fields: " + fields.length + " (need 16)";

        if (fields[0].trim().isEmpty())
            return "Product ID is empty";

        if (fields[1].trim().isEmpty())
            return "Name is empty";

        if (fields[2].trim().isEmpty())
            return "Brand is empty";

        try {
            double price = Double.parseDouble(fields[3].trim());
            if (price < 0)
                return "Price is negative: " + price;
        } catch (NumberFormatException e) {
            return "Invalid price: " + fields[3];
        }

        try {
            int quantity = Integer.parseInt(fields[4].trim());
            if (quantity < 0)
                return "Quantity is negative: " + quantity;
        } catch (NumberFormatException e) {
            return "Invalid quantity: " + fields[4];
        }

        String type = fields[6].trim();
        if (!type.equals("LAPTOP") &&
                !type.equals("PHONE") &&
                !type.equals("ACCESSORY"))
            return "Invalid product type: " + type;

        return "Unknown validation error";
    }
}
