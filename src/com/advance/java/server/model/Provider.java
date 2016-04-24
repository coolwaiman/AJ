package com.advance.java.server.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Provider implements Comparable<Provider> {
    private int providerId;
    private String providerName;
    private List<Product> products;

    @OneToMany(mappedBy = "provider")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ProviderId", nullable = false)
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "ProdviderName", nullable = false, length = 255)
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String prodviderName) {
        this.providerName = prodviderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provider provider = (Provider) o;

        if (providerId != provider.providerId) return false;
        if (providerName != null ? !providerName.equals(provider.providerName) : provider.providerName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerId;
        result = 31 * result + (providerName != null ? providerName.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Provider o) {
        return this.getProviderId() - o.getProviderId();
    }
}
