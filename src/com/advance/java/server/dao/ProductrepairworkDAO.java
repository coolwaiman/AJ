package com.advance.java.server.dao;

import com.advance.java.server.model.Productrepairwork;

import java.util.List;

/**
 * Created by rAYMOND on 4/23/2016.
 */
public class ProductrepairworkDAO extends DAO {
    public static List<Productrepairwork> getAll() {
        return getSession().createQuery("from Productrepairwork ").list();
    }

    public static Productrepairwork getById(int id) {
        return (Productrepairwork) getSession()
                .createQuery("from Productrepairwork where repairWorkId = :id")
                .setInteger("id", id)
                .uniqueResult();
    }

    public static List<Productrepairwork> getByProductId(int id) {
        return getSession()
                .createQuery("from Productrepairwork where storeProduct.product.productId = :id")
                .setInteger("id", id)
                .list();
    }


    public static List<Productrepairwork> getRepairWorkSolved(boolean isSolved) {
        if(isSolved) {
            return getSession()
                .createQuery("from Productrepairwork  where solvedDate is not null")
                .list();
        } else {
            return getSession()
                    .createQuery("from Productrepairwork  where solvedDate is null")
                    .list();
        }
    }



}
