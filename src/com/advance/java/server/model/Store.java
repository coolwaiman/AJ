package com.advance.java.server.model;

import javax.persistence.*;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Store {
    private int storeId;
    private String storeName;
    private String storeLocation;
    private String storeContact;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StoreId", nullable = false)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "StoreName", nullable = false, length = 255)
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Basic
    @Column(name = "StoreLocation", nullable = false, length = 255)
    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    @Basic
    @Column(name = "StoreContact", nullable = true, length = 30)
    public String getStoreContact() {
        return storeContact;
    }

    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (storeId != store.storeId) return false;
        if (storeName != null ? !storeName.equals(store.storeName) : store.storeName != null) return false;
        if (storeLocation != null ? !storeLocation.equals(store.storeLocation) : store.storeLocation != null)
            return false;
        if (storeContact != null ? !storeContact.equals(store.storeContact) : store.storeContact != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (storeLocation != null ? storeLocation.hashCode() : 0);
        result = 31 * result + (storeContact != null ? storeContact.hashCode() : 0);
        return result;
    }
}
