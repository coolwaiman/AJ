package com.advance.java.server.dao;

import com.advance.java.server.model.Store;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class StoreDAO extends DAO {
    public static List<Store> getAll() {
        return getSession()
                .createQuery("from Store ")
                .list();
    }

    public static Store getById(int id) {
        return (Store) getSession()
                .createQuery("from Store where storeId = :id")
                .setInteger("id", id)
                .uniqueResult();
    }

}
