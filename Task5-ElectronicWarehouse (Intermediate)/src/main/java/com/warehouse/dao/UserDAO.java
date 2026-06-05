package com.warehouse.dao;

import com.warehouse.config.HibernateUtil;
import com.warehouse.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {

    public void save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    public User findByUsername(String username) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean usernameExists(String username) {
        return findByUsername(username) != null;
    }
}
