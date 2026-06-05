package com.warehouse.menu;

import com.warehouse.dao.PhoneDAO;
import com.warehouse.model.Phone;
import com.warehouse.service.AuthService;
import java.util.List;
import java.util.Scanner;

public class PhoneMenu {

    private final Scanner scanner;
    private final PhoneDAO phoneDAO = new PhoneDAO();
    private final AuthService authService;

    public PhoneMenu(Scanner scanner, AuthService authService) {
        this.scanner = scanner;
        this.authService = authService;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\nPHONE OPERATIONS");

            if (authService.isAdmin()) {
                System.out.println("1. Add Phone [ADMIN]");
                System.out.println("2. Update Phone [ADMIN]");
                System.out.println("3. Delete Phone [ADMIN]");
            } else {
                System.out.println("1. Add Phone [ADMIN ONLY]");
                System.out.println("2. Update Phone [ADMIN ONLY]");
                System.out.println("3. Delete Phone [ADMIN ONLY]");
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
                        addPhone();
                    }
                    break;
                case "2":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        updatePhone();
                    }
                    break;
                case "3":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        deletePhone();
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

    private void addPhone() {
        System.out.println("\nAdd Phone");
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
            double screenSize = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Battery (mAh): ");
            int batteryCapacity = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Camera (MP): ");
            int cameraResolution = Integer.parseInt(scanner.nextLine().trim());

            if (price < 0 || quantity < 0) {
                System.out.println("Price/Quantity cannot be negative!");
                return;
            }
            if (name.isEmpty() || brand.isEmpty()) {
                System.out.println("Name/Brand cannot be empty!");
                return;
            }

            Phone phone = new Phone(id, name, brand, price, quantity, warranty, screenSize, batteryCapacity, cameraResolution);
            phoneDAO.save(phone);
            System.out.println("Phone added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    private void updatePhone() {
        System.out.print("\nEnter Phone ID to update: ");
        String id = scanner.nextLine().trim();
        Phone phone = phoneDAO.findById(id);

        if (phone == null) {
            System.out.println("Phone not found: " + id);
            return;
        }

        System.out.print("New Name (Enter to keep '" + phone.getName() + "'): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) phone.setName(name);

        System.out.print("New Price (Enter to keep " + phone.getPrice() + "): ");
        String priceInput = scanner.nextLine().trim();
        if (!priceInput.isEmpty()) {
            double price = Double.parseDouble(priceInput);
            if (price >= 0) phone.setPrice(price);
            else System.out.println("Price cannot be negative!");
        }

        System.out.print("New Quantity (Enter to keep " + phone.getQuantity() + "): ");
        String quantityInput = scanner.nextLine().trim();
        if (!quantityInput.isEmpty()) {
            int quantity = Integer.parseInt(quantityInput);
            if (quantity >= 0) phone.setQuantity(quantity);
            else System.out.println("Quantity cannot be negative!");
        }

        phoneDAO.update(phone);
        System.out.println("Phone updated successfully!");
    }

    private void deletePhone() {
        System.out.print("\nEnter Phone ID to delete: ");
        String id = scanner.nextLine().trim();

        Phone phone = phoneDAO.findById(id);
        if (phone == null) {
            System.out.println("Phone not found: " + id);
            return;
        }

        System.out.print("Confirm delete '" + phone.getName() + "'? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            phoneDAO.delete(id);
            System.out.println("Deleted: " + id);
        } else {
            System.out.println("Delete cancelled!");
        }
    }

    private void findById() {
        System.out.print("\nEnter Phone ID: ");
        String id = scanner.nextLine().trim();
        Phone phone = phoneDAO.findById(id);
        if (phone != null) {
            System.out.println("\n" + phone);
        } else {
            System.out.println("Phone not found: " + id);
        }
    }

    private void findByName() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine().trim();
        List<Phone> phones = phoneDAO.findByName(name);
        if (phones == null || phones.isEmpty()) {
            System.out.println("No phones found!");
        } else {
            System.out.println("\nFound " + phones.size() + " phone(s):");
            phones.forEach(phone -> System.out.println(phone + "\n"));
        }
    }

    private void showAll() {
        List<Phone> phones = phoneDAO.findAll();
        if (phones == null || phones.isEmpty()) {
            System.out.println("No phones in database!");
        } else {
            System.out.println("\nALL PHONES (" + phones.size() + ")");
            phones.forEach(phone -> System.out.println(phone + "\n"));
        }
    }
}
