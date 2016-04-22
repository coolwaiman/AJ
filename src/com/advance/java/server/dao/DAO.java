package com.advance.java.server.dao;

import com.advance.java.server.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class DAO<T> {
    private static Session s;
    public static Session getSession()
    {
        s = HibernateUtil.getSessionFactory().openSession();
        return s;
    }
    public static boolean save(Object object) {
        if(s == null || !s.isConnected()) getSession();
        try {
            s.beginTransaction();
            s.save(object);
            s.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            s.getTransaction().rollback();
            return false;
        }
    }

    public static boolean update(Object object) {
        if(s == null || !s.isConnected()) getSession();
        try {
            s.beginTransaction();
            s.update(object);
            s.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            s.getTransaction().rollback();
            return false;
        }
    }
}
