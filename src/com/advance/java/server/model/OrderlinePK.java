package com.advance.java.server.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class OrderlinePK implements Serializable {
    private int orderId;
    private int productSn;

    @Column(name = "OrderId", nullable = false)
    @Id
    public int getCusOrder() {
        return orderId;
    }

    public void setCusOrder(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "ProductSn", nullable = false)
    @Id
    public int getStoreProduct() {
        return productSn;
    }

    public void setStoreProduct(int productSn) {
        this.productSn = productSn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderlinePK that = (OrderlinePK) o;

        if (orderId != that.orderId) return false;
        if (productSn != that.productSn) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + productSn;
        return result;
    }
}
