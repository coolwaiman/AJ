package com.advance.java.server.dao;

import com.advance.java.server.model.Staff;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class StaffDAO extends DAO {
    public static List<Staff> getAll() {
        return getSession().createQuery("from Staff")
                .list();
    }

    public static Staff getByUsername(String loginName) {
        return (Staff) getSession().createQuery("from Staff where staffAccount.username = :username")
                .setString("username", loginName)
                .uniqueResult();

    }
}
