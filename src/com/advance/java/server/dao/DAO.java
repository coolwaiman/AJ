package com.advance.java.server.dao;

import com.advance.java.server.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class DAO {
    public static Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
