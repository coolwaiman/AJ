package com.advance.java.server.dao;

import com.advance.java.server.model.Storestockwarning;

import java.util.List;

/**
 * Created by rAYMOND on 4/24/2016.
 */
public class WarningLevelDAO extends DAO {
    public static Storestockwarning getByStoreAndProduct(int storeId, int productId) {
        return (Storestockwarning) getSession()
                .createQuery("from Storestockwarning where store.storeId = :sid and product.productId = :pid")
                .setInteger("sid", storeId)
                .setInteger("pid", productId)
                .uniqueResult();
    }

    public static List<Storestockwarning> getAll() {
        return getSession()
                .createQuery("from Storestockwarning")
                .list();
    }

}
