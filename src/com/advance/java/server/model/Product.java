package com.advance.java.server.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private Category category;
    private Provider provider;
    private List<Storeproduct> storeProducts;


    @OneToMany(mappedBy = "product")
    public List<Storeproduct> getStoreProducts() {
        return storeProducts;
    }

    public void setStoreProducts(List<Storeproduct> storeProducts) {
        this.storeProducts = storeProducts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "ProductName", nullable = false, length = 255)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "ProductDescription", nullable = true, length = 255)
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Basic
    @Column(name = "ProductPrice", nullable = false, precision = 0)
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "ProviderId", nullable = false)
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (Double.compare(product.productPrice, productPrice) != 0) return false;
        if (category != product.category) return false;
        if (provider != product.provider) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (productDescription != null ? !productDescription.equals(product.productDescription) : product.productDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productDescription != null ? productDescription.hashCode() : 0);
        temp = Double.doubleToLongBits(productPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + category.hashCode();
        result = 31 * result + provider.hashCode();
        return result;
    }
}
