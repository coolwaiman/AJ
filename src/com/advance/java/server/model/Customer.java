package com.advance.java.server.model;

import javax.persistence.*;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Customer {
    private int cusId;
    private String cusName;
    private String cusAddress;
    private String cusPhone;
    private String cusEmail;
    private Account cusAccount;
    private Character gender;

    @Basic
    @Column(name = "CusGender")
    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CusId", nullable = false)
    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    @Basic
    @Column(name = "CusName", nullable = false, length = 255)
    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Basic
    @Column(name = "CusAddress", nullable = true, length = 255)
    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    @Basic
    @Column(name = "CusPhone", nullable = true, length = 30)
    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    @Basic
    @Column(name = "CusEmail", nullable = false, length = 255)
    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    @OneToOne
    @JoinColumn(name = "CusAccountId", nullable = true)
    public Account getCusAccount() {
        return cusAccount;
    }

    public void setCusAccount(Account cusAccountId) {
        this.cusAccount = cusAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (cusId != customer.cusId) return false;
        if (cusName != null ? !cusName.equals(customer.cusName) : customer.cusName != null) return false;
        if (cusAddress != null ? !cusAddress.equals(customer.cusAddress) : customer.cusAddress != null) return false;
        if (cusPhone != null ? !cusPhone.equals(customer.cusPhone) : customer.cusPhone != null) return false;
        if (cusEmail != null ? !cusEmail.equals(customer.cusEmail) : customer.cusEmail != null) return false;
        if (cusAccount != null ? !cusAccount.equals(customer.cusAccount) : customer.cusAccount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cusId;
        result = 31 * result + (cusName != null ? cusName.hashCode() : 0);
        result = 31 * result + (cusAddress != null ? cusAddress.hashCode() : 0);
        result = 31 * result + (cusPhone != null ? cusPhone.hashCode() : 0);
        result = 31 * result + (cusEmail != null ? cusEmail.hashCode() : 0);
        result = 31 * result + (cusAccount != null ? cusAccount.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
