package com.advance.java.server.dao;

import com.advance.java.server.model.Product;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class ProductDAO extends DAO {
    public static Product getById(int id) {
        return (Product) getSession().createQuery("from Product where productId = :pId")
                .setInteger("pId", id)
                .uniqueResult();
    }
}
