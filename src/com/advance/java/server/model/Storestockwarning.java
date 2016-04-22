package com.advance.java.server.model;

import javax.persistence.*;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
@IdClass(StorestockwarningPK.class)
public class Storestockwarning {
    private int storeId;
    private int productId;
    private int warningLevel;

    @Id
    @Column(name = "StoreId", nullable = false)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Id
    @Column(name = "ProductId", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "WarningLevel", nullable = false)
    public int getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(int warningLevel) {
        this.warningLevel = warningLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storestockwarning that = (Storestockwarning) o;

        if (storeId != that.storeId) return false;
        if (productId != that.productId) return false;
        if (warningLevel != that.warningLevel) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId;
        result = 31 * result + productId;
        result = 31 * result + warningLevel;
        return result;
    }
}
