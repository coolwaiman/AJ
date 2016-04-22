package com.advance.java.server.dao;

import com.advance.java.server.model.Store;
import com.advance.java.server.model.Storeproduct;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class StoreProductDAO extends DAO {
    public static Storeproduct getBySn(int sn) {
        return (Storeproduct) getSession().createQuery("from Storeproduct where productSn = :sn")
                .setInteger("sn", sn)
                .uniqueResult();
    }


    public static List<Storeproduct> getByStore(int storeId) {
        return  getSession().createQuery("from Storeproduct where store.storeId =:id")
                .setInteger("id", storeId).list();
    }

    public static List<Storeproduct> getByProduct(int productId) {
        return getSession().createQuery("from Storeproduct  where product.productId = :id")
                .setInteger("id", productId)
                .list();
    }

    public static List<Storeproduct> getByStoreAndProduct(int storeId, int productId) {
        return getSession().createQuery("from Storeproduct  " +
                "where product.productId = :pid and " +
                "store.storeId = :sid")
                .setInteger("sid", storeId)
                .setInteger("pid", productId)
                .list();
    }

}
