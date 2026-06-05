package com.warehouse.dao;

import com.warehouse.config.HibernateUtil;
import com.warehouse.model.Accessory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AccessoryDAO {

    public void save(Accessory accessory) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(accessory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void update(Accessory accessory) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(accessory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void delete(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Accessory accessory = session.get(Accessory.class, id);
            if (accessory != null) session.remove(accessory);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Accessory findById(String id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.get(Accessory.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Accessory> findByName(String name) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Accessory WHERE " + "LOWER(name) LIKE LOWER(:name)", Accessory.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Accessory> findAll() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Accessory", Accessory.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
