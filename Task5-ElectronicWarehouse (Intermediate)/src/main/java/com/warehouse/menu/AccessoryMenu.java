package com.warehouse.menu;

import com.warehouse.dao.AccessoryDAO;
import com.warehouse.model.Accessory;
import com.warehouse.service.AuthService;
import java.util.List;
import java.util.Scanner;

public class AccessoryMenu {

    private final Scanner scanner;
    private final AccessoryDAO accessoryDAO = new AccessoryDAO();
    private final AuthService authService;

    public AccessoryMenu(Scanner scanner, AuthService authService) {
        this.scanner = scanner;
        this.authService = authService;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\nACCESSORY OPERATIONS");

            if (authService.isAdmin()) {
                System.out.println("1. Add Accessory [ADMIN]");
                System.out.println("2. Update Accessory [ADMIN]");
                System.out.println("3. Delete Accessory [ADMIN]");
            } else {
                System.out.println("1. Add Accessory [ADMIN ONLY]");
                System.out.println("2. Update Accessory [ADMIN ONLY]");
                System.out.println("3. Delete Accessory [ADMIN ONLY]");
            }

            System.out.println("4. Find by ID");
            System.out.println("5. Find by Name");
            System.out.println("6. Show All");
            System.out.println("7. Back");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        addAccessory();
                    }
                    break;
                case "2":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        updateAccessory();
                    }
                    break;
                case "3":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        deleteAccessory();
                    }
                    break;
                case "4": findById();      break;
                case "5": findByName();    break;
                case "6": showAll();       break;
                case "7": running = false; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private void addAccessory() {
        System.out.println("\nAdd Accessory");
        try {
            System.out.print("Product ID: ");
            String id = scanner.nextLine().trim();
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Brand: ");
            String brand = scanner.nextLine().trim();
            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Warranty Years: ");
            int warranty = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Connector Type: ");
            String connector = scanner.nextLine().trim();
            System.out.print("Port Count: ");
            int ports = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Compatible With: ");
            String compatible = scanner.nextLine().trim();

            if (price < 0 || quantity < 0) {
                System.out.println("Price/Quantity cannot be negative!");
                return;
            }
            if (name.isEmpty() || brand.isEmpty()) {
                System.out.println("Name/Brand cannot be empty!");
                return;
            }

            Accessory accessory = new Accessory(id, name, brand, price, quantity, warranty, connector, ports, compatible);
            accessoryDAO.save(accessory);
            System.out.println("Accessory added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    private void updateAccessory() {
        System.out.print("\nEnter Accessory ID to update: ");
        String id = scanner.nextLine().trim();
        Accessory accessory = accessoryDAO.findById(id);

        if (accessory == null) {
            System.out.println("Accessory not found: " + id);
            return;
        }

        System.out.print("New Name (Enter to keep '" + accessory.getName() + "'): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) accessory.setName(name);

        System.out.print("New Price (Enter to keep " + accessory.getPrice() + "): ");
        String priceInput = scanner.nextLine().trim();
        if (!priceInput.isEmpty()) {
            double price = Double.parseDouble(priceInput);
            if (price >= 0) accessory.setPrice(price);
            else System.out.println("Price cannot be negative!");
        }

        System.out.print("New Quantity (Enter to keep " + accessory.getQuantity() + "): ");
        String quantityInput = scanner.nextLine().trim();
        if (!quantityInput.isEmpty()) {
            int quantity = Integer.parseInt(quantityInput);
            if (quantity >= 0) accessory.setQuantity(quantity);
            else System.out.println("Quantity cannot be negative!");
        }

        accessoryDAO.update(accessory);
        System.out.println("Accessory updated successfully!");
    }

    private void deleteAccessory() {
        System.out.print("\nEnter Accessory ID to delete: ");
        String id = scanner.nextLine().trim();

        Accessory accessory = accessoryDAO.findById(id);
        if (accessory == null) {
            System.out.println("Accessory not found: " + id);
            return;
        }

        System.out.print("Confirm delete '" + accessory.getName() + "'? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            accessoryDAO.delete(id);
            System.out.println("Deleted: " + id);
        } else {
            System.out.println("Delete cancelled!");
        }
    }

    private void findById() {
        System.out.print("\nEnter Accessory ID: ");
        String id = scanner.nextLine().trim();
        Accessory accessory = accessoryDAO.findById(id);
        if (accessory != null) {
            System.out.println("\n" + accessory);
        } else {
            System.out.println("Accessory not found: " + id);
        }
    }

    private void findByName() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine().trim();
        List<Accessory> accessories = accessoryDAO.findByName(name);
        if (accessories == null || accessories.isEmpty()) {
            System.out.println("No accessories found!");
        } else {
            System.out.println("\nFound " + accessories.size() + " accessory(s):");
            accessories.forEach(accessory -> System.out.println(accessory + "\n"));
        }
    }

    private void showAll() {
        List<Accessory> accessories = accessoryDAO.findAll();
        if (accessories == null || accessories.isEmpty()) {
            System.out.println("No accessories in database!");
        } else {
            System.out.println("\nALL ACCESSORIES (" + accessories.size() + ")");
            accessories.forEach(accessory -> System.out.println(accessory + "\n"));
        }
    }
}
