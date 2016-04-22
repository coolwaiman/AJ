package com.advance.java.server.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Cusorder {
    private int orderId;
    private int staffId;
    private int cusId;
    private Double orderDiscount;
    private Byte isOrderDelivery;
    private String deliveryAddress;
    private Date deliveryDate;
    private Timestamp orderDate;
    private Double orderPrePaid;

    @Id
    @Column(name = "OrderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "StaffId", nullable = false)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "CusId", nullable = false)
    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    @Basic
    @Column(name = "OrderDiscount", nullable = true, precision = 0)
    public Double getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(Double orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    @Basic
    @Column(name = "IsOrderDelivery", nullable = true)
    public Byte getIsOrderDelivery() {
        return isOrderDelivery;
    }

    public void setIsOrderDelivery(Byte isOrderDelivery) {
        this.isOrderDelivery = isOrderDelivery;
    }

    @Basic
    @Column(name = "DeliveryAddress", nullable = true, length = 255)
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Basic
    @Column(name = "DeliveryDate", nullable = true)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Basic
    @Column(name = "OrderDate", nullable = false)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "OrderPrePaid", nullable = true, precision = 0)
    public Double getOrderPrePaid() {
        return orderPrePaid;
    }

    public void setOrderPrePaid(Double orderPrePaid) {
        this.orderPrePaid = orderPrePaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cusorder cusorder = (Cusorder) o;

        if (orderId != cusorder.orderId) return false;
        if (staffId != cusorder.staffId) return false;
        if (cusId != cusorder.cusId) return false;
        if (orderDiscount != null ? !orderDiscount.equals(cusorder.orderDiscount) : cusorder.orderDiscount != null)
            return false;
        if (isOrderDelivery != null ? !isOrderDelivery.equals(cusorder.isOrderDelivery) : cusorder.isOrderDelivery != null)
            return false;
        if (deliveryAddress != null ? !deliveryAddress.equals(cusorder.deliveryAddress) : cusorder.deliveryAddress != null)
            return false;
        if (deliveryDate != null ? !deliveryDate.equals(cusorder.deliveryDate) : cusorder.deliveryDate != null)
            return false;
        if (orderDate != null ? !orderDate.equals(cusorder.orderDate) : cusorder.orderDate != null) return false;
        if (orderPrePaid != null ? !orderPrePaid.equals(cusorder.orderPrePaid) : cusorder.orderPrePaid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + staffId;
        result = 31 * result + cusId;
        result = 31 * result + (orderDiscount != null ? orderDiscount.hashCode() : 0);
        result = 31 * result + (isOrderDelivery != null ? isOrderDelivery.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderPrePaid != null ? orderPrePaid.hashCode() : 0);
        return result;
    }
}
