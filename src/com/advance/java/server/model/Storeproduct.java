package com.advance.java.server.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Storeproduct {
    private int productSn;
    private int storeId;
    private Integer productId;

    @Id
    @Column(name = "ProductSn", nullable = false)
    public int getProductSn() {
        return productSn;
    }

    public void setProductSn(int productSn) {
        this.productSn = productSn;
    }

    @Basic
    @Column(name = "StoreId", nullable = false)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "ProductId", nullable = true)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storeproduct that = (Storeproduct) o;

        if (productSn != that.productSn) return false;
        if (storeId != that.storeId) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productSn;
        result = 31 * result + storeId;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
