package com.student.dao;

import com.student.model.Student;
import com.student.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StudentDAO {
    // Get all students
    public List<Student> getAllStudents() {
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            return session.createQuery(
                    "SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses",
                    Student.class
            ).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // Get student by ID
    public Student getStudentById(int rollNo) {
        Session session = null;

        try {
            session = HibernateUtil.openSession();

            return session.createQuery(
                            "SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.rollNo = :id",
                            Student.class).setParameter("id", rollNo).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // Save Student
    public void saveStudent(Student student) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            System.out.println("Student saved: " + student.getName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // Update Student
    public void updateStudent(Student student) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
            System.out.println("Student updated: " + student.getName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // Delete Student
    public void deleteStudent(int rollNo) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, rollNo);
            if (student != null) {
                session.remove(student);
                System.out.println("Student deleted: " + rollNo);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
