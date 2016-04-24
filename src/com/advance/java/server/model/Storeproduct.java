package com.advance.java.server.model;

import javax.persistence.*;
import javax.persistence.criteria.Order;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Storeproduct {
    private int productSn;
    private Store store;
    private Product product;
    private Orderline orderline;

    @OneToOne(mappedBy = "storeProduct")
    public Orderline getOrderline() {
        return orderline;
    }

    public void setOrderline(Orderline orderline) {
        this.orderline = orderline;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductSn", nullable = false)
    public int getProductSn() {
        return productSn;
    }

    public void setProductSn(int productSn) {
        this.productSn = productSn;
    }

    @ManyToOne
    @JoinColumn(name = "StoreId", nullable = false)
    public Store getStore() {
        return store;
    }

    public void setStore(Store storeId) {
        this.store = storeId;
    }

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = true)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productId) {
        this.product = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storeproduct that = (Storeproduct) o;

        if (productSn != that.productSn) return false;
        if (store != that.store) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productSn;
        result = 31 * result + store.hashCode();
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
