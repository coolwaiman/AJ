package com.advance.java.server.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Cusorder implements Comparable<Cusorder> {
    private int orderId;
    private Staff staff;
    private Customer customer;
    private Double orderDiscount;
    private Byte isOrderDelivery;
    private String deliveryAddress;
    private Date deliveryDate;
    private Timestamp orderDate;
    private Double orderPrePaid;
    private List<Orderline> orderlines;

    @OneToMany(mappedBy = "cusOrder")
    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @ManyToOne
    @JoinColumn(name = "StaffId", nullable = false)
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staffId) {
        this.staff = staffId;
    }

    @ManyToOne
    @JoinColumn(name = "CusId", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer cusId) {
        this.customer = cusId;
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
        if (staff != cusorder.staff) return false;
        if (customer != cusorder.customer) return false;
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
        result = 31 * result + staff.hashCode();
        result = 31 * result + customer.hashCode();
        result = 31 * result + (orderDiscount != null ? orderDiscount.hashCode() : 0);
        result = 31 * result + (isOrderDelivery != null ? isOrderDelivery.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderPrePaid != null ? orderPrePaid.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Cusorder o) {
        return this.getOrderId() - o.getOrderId();
    }
}
