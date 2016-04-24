package com.advance.java.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Productrepairwork implements Comparable<Productrepairwork>{
    private int repairWorkId;
    private Cusorder order;
    private Storeproduct storeProduct;
    private Staff createdStaff;
    private Staff assignedTechnician;
    private Timestamp createdDate;
    private Timestamp solvedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RepairWorkId", nullable = false)
    public int getRepairWorkId() {
        return repairWorkId;
    }

    public void setRepairWorkId(int repairWorkId) {
        this.repairWorkId = repairWorkId;
    }

    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    public Cusorder getOrder() {
        return order;
    }

    public void setOrder(Cusorder orderId) {
        this.order = orderId;
    }

    @ManyToOne
    @JoinColumn(name = "ProductSn", nullable = false)
    public Storeproduct getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(Storeproduct productSn) {
        this.storeProduct = productSn;
    }

    @ManyToOne
    @JoinColumn(name = "CreatedStaff", nullable = false)
    public Staff getCreatedStaff() {
        return createdStaff;
    }

    public void setCreatedStaff(Staff createdStaff) {
        this.createdStaff = createdStaff;
    }

    @ManyToOne
    @JoinColumn(name = "AssignedTechnician", nullable = true)
    public Staff getAssignedTechnician() {
        return assignedTechnician;
    }

    public void setAssignedTechnician(Staff assignedTechnician) {
        this.assignedTechnician = assignedTechnician;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = false)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "SolvedDate", nullable = true)
    public Timestamp getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(Timestamp solvedDate) {
        this.solvedDate = solvedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Productrepairwork that = (Productrepairwork) o;

        if (repairWorkId != that.repairWorkId) return false;
        if (order != that.order) return false;
        if (storeProduct != that.storeProduct) return false;
        if (createdStaff != that.createdStaff) return false;
        if (assignedTechnician != null ? !assignedTechnician.equals(that.assignedTechnician) : that.assignedTechnician != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (solvedDate != null ? !solvedDate.equals(that.solvedDate) : that.solvedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repairWorkId;
        result = 31 * result + order.hashCode();
        result = 31 * result + storeProduct.hashCode();
        result = 31 * result + createdStaff.hashCode();
        result = 31 * result + (assignedTechnician != null ? assignedTechnician.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (solvedDate != null ? solvedDate.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Productrepairwork o) {
        return getRepairWorkId() - o.getRepairWorkId();
    }
}
