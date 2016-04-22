package com.advance.java.server.dao;

import com.advance.java.server.model.Provider;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class ProviderDAO extends DAO<Provider> {
    public static List<Provider> getAll() {
        return getSession().createQuery("from Provider").list();
    }
}
