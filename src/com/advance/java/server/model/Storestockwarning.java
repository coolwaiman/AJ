package com.advance.java.server.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Storestockwarning implements Serializable{
    private Store store;
    private Product product;
    private int warningLevel;

    @Id
    @ManyToOne
    @JoinColumn(name = "StoreId", nullable = false)
    public Store getStore() {
        return store;
    }

    public void setStore(Store storeId) {
        this.store = storeId;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productId) {
        this.product = productId;
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

        if (store != that.store) return false;
        if (product != that.product) return false;
        if (warningLevel != that.warningLevel) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = store.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + warningLevel;
        return result;
    }
}
