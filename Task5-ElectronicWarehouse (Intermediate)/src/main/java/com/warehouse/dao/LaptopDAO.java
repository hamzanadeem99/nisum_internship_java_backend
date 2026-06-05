package com.warehouse.dao;

import com.warehouse.config.HibernateUtil;
import com.warehouse.model.Laptop;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LaptopDAO {

    public void save(Laptop laptop) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(laptop);
            transaction.commit();
            System.out.println("Laptop saved: " + laptop.getName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void update(Laptop laptop) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(laptop);
            transaction.commit();
            System.out.println("Laptop updated: " + laptop.getName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void delete(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Laptop laptop = session.get(Laptop.class, id);
            if (laptop != null) session.remove(laptop);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Laptop findById(String id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.get(Laptop.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Laptop> findByName(String name) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Laptop WHERE " + "LOWER(name) LIKE LOWER(:name)", Laptop.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Laptop> findAll() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Laptop", Laptop.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
