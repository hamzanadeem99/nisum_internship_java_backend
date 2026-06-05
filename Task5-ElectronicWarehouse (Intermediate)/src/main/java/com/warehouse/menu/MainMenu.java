package com.warehouse.menu;

import com.warehouse.csv.CSVProcessor;
import com.warehouse.service.AuthService;
import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final AuthService authService = new AuthService();
    private final ProductMenu productMenu = new ProductMenu(scanner, authService);
    private final LaptopMenu laptopMenu = new LaptopMenu(scanner, authService);
    private final PhoneMenu phoneMenu = new PhoneMenu(scanner, authService);
    private final AccessoryMenu accessoryMenu = new AccessoryMenu(scanner, authService);
    private final CSVProcessor csvProcessor = new CSVProcessor();

    public void start() {
        System.out.println("ELECTRONICS WAREHOUSE MANAGEMENT");

        if (!showAuthMenu()) {
            System.out.println("Goodbye!");
            return;
        }

        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // ADMIN only
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        loadCSV();
                    }
                    break;
                case "2":
                    productMenu.show();
                    break;
                case "3":
                    laptopMenu.show();
                    break;
                case "4":
                    phoneMenu.show();
                    break;
                case "5":
                    accessoryMenu.show();
                    break;
                case "6":
                    // ADMIN only
                    if (!authService.isAdmin()) {
                        System.out.println("ADMIN access required!");
                    } else {
                        authService.promoteToAdmin(scanner);
                    }
                    break;
                case "7":
                    authService.logout();
                    if (!showAuthMenu()) {
                        running = false;
                    }
                    break;
                case "8":
                    running = false;
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private boolean showAuthMenu() {
        while (true) {
            System.out.println("\n1. Login\n2. Register\n3. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    if (authService.login(scanner))
                        return true;
                    break;
                case "2":
                    authService.register(scanner);
                    break;
                case "3":
                    return false;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void showMainMenu() {

        // Show role in menu
        System.out.println("MAIN MENU [" + authService.getCurrentRole() + "]");

        // ADMIN options shown differently
        if (authService.isAdmin()) {
            System.out.println("1. Load Products from CSV [ADMIN]");
        } else {
            System.out.println("1. Load Products from CSV [ADMIN ONLY]");
        }

        System.out.println("2. Product Operations");
        System.out.println("3. Laptop Operations");
        System.out.println("4. Phone Operations");
        System.out.println("5. Accessory Operations");

        if (authService.isAdmin()) {
            System.out.println("6. Promote User to Admin [ADMIN]");
        }

        System.out.println("7. Switch User");
        System.out.println("8. Exit");
        System.out.print("Choice: ");
    }

    private void loadCSV() {
        System.out.print("Enter CSV file path: ");
        String path = scanner.nextLine().trim();
        csvProcessor.processCSV(path);
    }
}
