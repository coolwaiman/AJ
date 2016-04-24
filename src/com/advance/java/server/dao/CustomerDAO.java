package com.advance.java.server.dao;

import com.advance.java.server.model.Account;
import com.advance.java.server.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class CustomerDAO extends DAO {
    public static List<Customer> getAll() {
        return getSession()
                .createQuery("from Customer")
                .list();
    }

    public static Customer getById(int id) {
        return (Customer) getSession()
                .createQuery("from Customer where cusId = :id")
                .setInteger("id", id)
                .uniqueResult();
    }

    public static Customer getByUsername(String username) {
        return (Customer) getSession()
                .createQuery("from Customer where cusAccount.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

    public static boolean insertCus(Customer c, Account a) {
        Session s = getSession();
            try{
                s.beginTransaction();
                s.save(a);
                s.save(c);
                s.getTransaction().commit();
                return true;
            } catch (HibernateException e) {
                s.getTransaction().rollback();
                e.printStackTrace();
                return false;
            }

    }
}
