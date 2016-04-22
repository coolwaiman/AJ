package com.advance.java.server.dao;

import com.advance.java.server.model.Cusorder;
import com.advance.java.server.model.Orderline;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class CusOrderDAO extends DAO {
    public static boolean makeOrder(Cusorder cusorder, List<Orderline> orderlines) {
        Session session = getSession();
        session.beginTransaction();
        session.save(cusorder);
        try {
            for (Orderline orderline : orderlines) {
                session.save(orderline);
            }
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }
}
