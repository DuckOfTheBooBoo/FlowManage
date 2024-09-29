/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.model.pojo.Task;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pc
 */
public class TaskDAO {
    public List<Task> getTasks(int projectId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Task> tasks = null;
        try {
            Query query = session.createQuery("FROM task WHERE project_id = :projectId");
            query.setParameter("projectId", projectId);
            tasks = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return tasks;
    }
    
    public boolean addTask(Task newTask) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.save(newTask);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public void deleteTask(Task targetTask) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(targetTask);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
