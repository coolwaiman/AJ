package com.advance.java.server.model;

import javax.persistence.*;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
@IdClass(OrderlinePK.class)
public class Orderline {
    private int orderId;
    private int productSn;
    private double productPrice;

    @Id
    @Column(name = "OrderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "ProductSn", nullable = false)
    public int getProductSn() {
        return productSn;
    }

    public void setProductSn(int productSn) {
        this.productSn = productSn;
    }

    @Basic
    @Column(name = "ProductPrice", nullable = false, precision = 0)
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderline orderline = (Orderline) o;

        if (orderId != orderline.orderId) return false;
        if (productSn != orderline.productSn) return false;
        if (Double.compare(orderline.productPrice, productPrice) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + productSn;
        temp = Double.doubleToLongBits(productPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
