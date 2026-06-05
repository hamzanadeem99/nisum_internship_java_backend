package com.warehouse.menu;

import com.warehouse.dao.ProductDAO;
import com.warehouse.model.Product;
import com.warehouse.service.AuthService;
import java.util.List;
import java.util.Scanner;

public class ProductMenu {

    private final Scanner scanner;
    private final ProductDAO productDAO = new ProductDAO();
    private final AuthService authService;

    public ProductMenu(Scanner scanner, AuthService authService) {
        this.scanner = scanner;
        this.authService = authService;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\nPRODUCT OPERATIONS");
            System.out.println("1. Find by ID");
            System.out.println("2. Find by Name");
            System.out.println("3. Show All Products");

            if (authService.isAdmin()) {
                System.out.println("4. Delete Product [ADMIN]");
            } else {
                System.out.println("4. Delete Product [ADMIN ONLY]");
            }

            System.out.println("5. Back");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": findById();      break;
                case "2": findByName();    break;
                case "3": showAll();       break;
                case "4":
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        delete();
                    }
                    break;
                case "5": running = false; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private void findById() {
        System.out.print("\nEnter Product ID: ");
        String id = scanner.nextLine().trim();
        Product product = productDAO.findById(id);
        if (product != null) {
            System.out.println("\n" + product);
        } else {
            System.out.println("Product not found: " + id);
        }
    }

    private void findByName() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine().trim();
        List<Product> products = productDAO.findByName(name);
        if (products == null || products.isEmpty()) {
            System.out.println("No products found!");
        } else {
            System.out.println("\nFound " + products.size() + " product(s):");
            products.forEach(product -> System.out.println(product + "\n"));
        }
    }

    private void showAll() {
        List<Product> products = productDAO.findAll();
        if (products == null || products.isEmpty()) {
            System.out.println("No products in database!");
        } else {
            System.out.println("\nALL PRODUCTS (" + products.size() + ")");
            products.forEach(product -> System.out.println(product + "\n"));
        }
    }

    private void delete() {
        System.out.print("\nEnter Product ID to delete: ");
        String id = scanner.nextLine().trim();

        Product product = productDAO.findById(id);
        if (product == null) {
            System.out.println("Product not found: " + id);
            return;
        }

        System.out.print("Confirm delete '" + product.getName() + "'? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            productDAO.delete(id);
            System.out.println("Deleted: " + id);
        } else {
            System.out.println("Delete cancelled!");
        }
    }
}
