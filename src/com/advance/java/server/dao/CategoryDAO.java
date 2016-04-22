package com.advance.java.server.dao;

import com.advance.java.server.model.Category;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class CategoryDAO extends DAO {
    public static Category getByName(String name) {
        return (Category) getSession().createQuery("from Category where categoryName = :name")
                .setString("name", name)
                .uniqueResult();
    }

}
