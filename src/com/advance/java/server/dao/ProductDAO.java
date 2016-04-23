package com.advance.java.server.dao;

import com.advance.java.server.model.Product;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class ProductDAO extends DAO {
    public static Product getById(int id) {
        return (Product) getSession().createQuery("from Product where productId = :pId")
                .setInteger("pId", id)
                .uniqueResult();
    }

    public static List<Product> getAll() {
        return getSession().createQuery("from Product").list();
    }

    public static List<Product> getByName(String name) {
        return getSession().createQuery("from Product where productName like :name")
                .setString("name", "%" + name + "%")
                .list();

    }
}
