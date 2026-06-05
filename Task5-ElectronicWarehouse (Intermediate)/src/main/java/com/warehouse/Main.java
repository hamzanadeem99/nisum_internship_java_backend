package com.warehouse;

import com.warehouse.config.HibernateUtil;
import com.warehouse.menu.MainMenu;

public class Main {

    public static void main(String[] args) {
        // Initialize Hibernate
        HibernateUtil.getSessionFactory();

        // Start application
        MainMenu menu = new MainMenu();
        menu.start();

        // Shutdown
        HibernateUtil.shutdown();
        System.out.println("Application closed.");
    }
}
