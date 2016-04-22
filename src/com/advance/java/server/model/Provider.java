package com.advance.java.server.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Provider {
    private int providerId;
    private String prodviderName;

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
    public String getProdviderName() {
        return prodviderName;
    }

    public void setProdviderName(String prodviderName) {
        this.prodviderName = prodviderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provider provider = (Provider) o;

        if (providerId != provider.providerId) return false;
        if (prodviderName != null ? !prodviderName.equals(provider.prodviderName) : provider.prodviderName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerId;
        result = 31 * result + (prodviderName != null ? prodviderName.hashCode() : 0);
        return result;
    }
}
