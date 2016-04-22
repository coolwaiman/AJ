package com.advance.java.server.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class StorestockwarningPK implements Serializable {
    private int storeId;
    private int productId;

    @Column(name = "StoreId", nullable = false)
    @Id
    public int getStore() {
        return storeId;
    }

    public void setStore(int storeId) {
        this.storeId = storeId;
    }

    @Column(name = "ProductId", nullable = false)
    @Id
    public int getProduct() {
        return productId;
    }

    public void setProduct(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorestockwarningPK that = (StorestockwarningPK) o;

        if (storeId != that.storeId) return false;
        if (productId != that.productId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId;
        result = 31 * result + productId;
        return result;
    }
}
