package com.warehouse.menu;

import com.warehouse.dao.LaptopDAO;
import com.warehouse.model.Laptop;
import com.warehouse.service.AuthService;
import java.util.List;
import java.util.Scanner;

public class LaptopMenu {

    private final Scanner scanner;
    private final LaptopDAO laptopDAO = new LaptopDAO();

    // AuthService for role checking
    private final AuthService authService;

    public LaptopMenu(Scanner scanner, AuthService authService) {
        this.scanner = scanner;
        this.authService = authService;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\nLAPTOP OPERATIONS");

            // Show options based on role
            if (authService.isAdmin()) {
                System.out.println("1. Add Laptop [ADMIN]");
                System.out.println("2. Update Laptop [ADMIN]");
                System.out.println("3. Delete Laptop [ADMIN]");
            } else {
                System.out.println("1. Add Laptop [ADMIN ONLY]");
                System.out.println("2. Update Laptop [ADMIN ONLY]");
                System.out.println("3. Delete Laptop [ADMIN ONLY]");
            }

            System.out.println("4. Find by ID");
            System.out.println("5. Find by Name");
            System.out.println("6. Show All");
            System.out.println("7. Back");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    // check admin
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        addLaptop();
                    }
                    break;
                case "2":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        updateLaptop();
                    }
                    break;
                case "3":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        deleteLaptop();
                    }
                    break;
                case "4": findById();  break;
                case "5": findByName(); break;
                case "6": showAll();   break;
                case "7": running = false; break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addLaptop() {
        System.out.println("\nAdd Laptop");
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
            System.out.print("Screen Size: ");
            double screen = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Processor: ");
            String cpu = scanner.nextLine().trim();
            System.out.print("RAM/Storage: ");
            String ram = scanner.nextLine().trim();

            if (price < 0 || quantity < 0) {
                System.out.println("Price/Quantity cannot be negative!");
                return;
            }
            if (name.isEmpty() || brand.isEmpty()) {
                System.out.println("Name/Brand cannot be empty!");
                return;
            }

            Laptop laptop = new Laptop(id, name, brand, price, quantity, warranty, screen, cpu, ram);
            laptopDAO.save(laptop);
            System.out.println("Laptop added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    private void updateLaptop() {
        System.out.print("\nEnter Laptop ID to update: ");
        String id = scanner.nextLine().trim();
        Laptop laptop = laptopDAO.findById(id);

        if (laptop == null) {
            System.out.println("Laptop not found: " + id);
            return;
        }

        System.out.print("New Name (Enter to keep '" + laptop.getName() + "'): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) laptop.setName(name);

        System.out.print("New Price (Enter to keep " + laptop.getPrice() + "): ");
        String priceInput = scanner.nextLine().trim();
        if (!priceInput.isEmpty()) {
            double price = Double.parseDouble(priceInput);
            if (price >= 0) laptop.setPrice(price);
            else System.out.println("Price cannot be negative!");
        }

        System.out.print("New Quantity (Enter to keep " + laptop.getQuantity() + "): ");
        String quantityInput = scanner.nextLine().trim();
        if (!quantityInput.isEmpty()) {
            int quantity = Integer.parseInt(quantityInput);
            if (quantity >= 0) laptop.setQuantity(quantity);
            else System.out.println("Quantity cannot be negative!");
        }

        laptopDAO.update(laptop);
        System.out.println("Laptop updated successfully!");
    }

    private void deleteLaptop() {
        System.out.print("\nEnter Laptop ID to delete: ");
        String id = scanner.nextLine().trim();

        Laptop laptop = laptopDAO.findById(id);
        if (laptop == null) {
            System.out.println("Laptop not found: " + id);
            return;
        }

        System.out.print("Confirm delete '" + laptop.getName() + "'? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            laptopDAO.delete(id);
            System.out.println("Deleted: " + id);
        } else {
            System.out.println("Delete cancelled!");
        }
    }

    private void findById() {
        System.out.print("\nEnter Laptop ID: ");
        String id = scanner.nextLine().trim();
        Laptop laptop = laptopDAO.findById(id);
        if (laptop != null) {
            System.out.println("\n" + laptop);
        } else {
            System.out.println("Laptop not found: " + id);
        }
    }

    private void findByName() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine().trim();
        List<Laptop> laptops = laptopDAO.findByName(name);
        if (laptops == null || laptops.isEmpty()) {
            System.out.println("No laptops found!");
        } else {
            System.out.println("\nFound " + laptops.size() + " laptop(s):");
            laptops.forEach(laptop -> System.out.println(laptop + "\n"));
        }
    }

    private void showAll() {
        List<Laptop> laptops = laptopDAO.findAll();
        if (laptops == null || laptops.isEmpty()) {
            System.out.println("No laptops in database!");
        } else {
            System.out.println("\nALL LAPTOPS (" + laptops.size() + ")");
            laptops.forEach(laptop -> System.out.println(laptop + "\n"));
        }
    }
}
