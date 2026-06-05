package com.student.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            System.out.println("Creating SessionFactory");
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            System.out.println("SessionFactory created!");
        } catch (Exception e) {
            System.out.println("SessionFactory FAILED!");
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            System.out.println("ERROR: SessionFactory is null!");
        }
        return sessionFactory;
    }

    public static Session openSession() {
        if (sessionFactory == null) {
            throw new RuntimeException(
                    "SessionFactory is null! Check hibernate.cfg.xml");
        }
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
