package com.warehouse.service;

import com.warehouse.dao.UserDAO;
import com.warehouse.model.User;
import java.util.Scanner;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();
    private User currentUser = null;

    public boolean login(Scanner scanner) {
        System.out.println("\nLOGIN");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        User user = userDAO.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("\nLogin successful, Welcome " + username + " [" + user.getRole() + "]");
            return true;
        }

        System.out.println("\nInvalid username or password!");
        return false;
    }

    public boolean register(Scanner scanner) {
        System.out.println("\nREGISTER");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return false;
        }

        if (userDAO.usernameExists(username)) {
            System.out.println("Username already exists!");
            return false;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty!");
            return false;
        }

        String role = "USER";

        User user = new User(username, password, role);
        userDAO.save(user);
        System.out.println("Registration successful! " + "You are registered as USER.");
        System.out.println("Contact admin for ADMIN access.");
        return true;
    }

    public boolean promoteToAdmin(
            Scanner scanner) {
        if (!isAdmin()) {
            System.out.println("Only ADMIN can promote users!");
            return false;
        }

        System.out.print("Enter username to promote: ");
        String username = scanner.nextLine().trim();

        User user = userDAO.findByUsername(username);
        if (user == null) {
            System.out.println("User not found: " + username);
            return false;
        }

        user.setRole("ADMIN");
        userDAO.update(user);
        System.out.println(username + " promoted to ADMIN!");
        return true;
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.getRole().equals("ADMIN");
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getCurrentRole() {
        if (currentUser == null) return "NONE";
        return currentUser.getRole();
    }
}
