package com.warehouse.dao;

import com.warehouse.config.HibernateUtil;
import com.warehouse.model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProductDAO {

    // Save
    public void save(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error saving product: " + e.getMessage());
        }
    }

    // Update
    public void update(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating product: " + e.getMessage());
        }
    }

    // Delete
    public void delete(String productId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.remove(product);
                System.out.println("Product deleted: " + productId);
            } else {
                System.out.println("Product not found: " + productId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting: " + e.getMessage());
        }
    }

    // Find by id
    public Product findById(String productId) {
        try (Session session = HibernateUtil.openSession()) {
            return session.get(Product.class, productId);
        } catch (Exception e) {
            System.err.println("Error finding product: " + e.getMessage());
            return null;
        }
    }

    // Find by name
    public List<Product> findByName(String name) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Product WHERE " + "LOWER(name) LIKE LOWER(:name)", Product.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Error finding by name: " + e.getMessage());
            return null;
        }
    }

    // Get all
    public List<Product> findAll() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Product", Product.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error getting all: " + e.getMessage());
            return null;
        }
    }
}
