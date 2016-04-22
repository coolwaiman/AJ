package com.advance.java.server.dao;

import com.advance.java.server.model.Customer;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class CustomerDAO extends DAO {
    public static List<Customer> getAll() {
        return getSession().createQuery("from Customer").list();
    }

    public static Customer getByUsername(String username) {
        return (Customer) getSession().createQuery("from Customer where cusAccount.username = :username")
                .setString("username", username)
                .uniqueResult();
    }
}
