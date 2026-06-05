package com.warehouse.dao;

import com.warehouse.config.HibernateUtil;
import com.warehouse.model.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PhoneDAO {

    public void save(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(phone);
            transaction.commit();
            System.out.println("Phone saved: " + phone.getName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void update(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(phone);
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
            Phone phone = session.get(Phone.class, id);
            if (phone != null) session.remove(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Phone findById(String id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.get(Phone.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Phone> findByName(String name) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Phone WHERE " + "LOWER(name) LIKE LOWER(:name)", Phone.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Phone> findAll() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery("FROM Phone", Phone.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
