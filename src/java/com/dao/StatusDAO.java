package com.dao;

import com.model.pojo.Status;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class StatusDAO {
    public StatusDAO() {}

    // Save a new Status record
    public Status saveStatus(Status status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(status);  // Hibernate will return the newly created entity with the generated ID
            transaction.commit();
            return status;  // Return the created Status object with the generated ID
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    // Get a Status by ID
    public Status getStatusById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Status stat = (Status) session.get(Status.class, id);
            return stat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}