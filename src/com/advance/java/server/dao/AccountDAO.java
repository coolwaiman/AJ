package com.advance.java.server.dao;

import com.advance.java.server.HibernateUtil;
import com.advance.java.server.model.Account;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class AccountDAO extends DAO{
    public static Account getByUsername(String username) {
        Account account = (Account) getSession()
                .createQuery("from Account where username = :username")
                .setString("username", username)
                .uniqueResult();

        return account;
    }
}
