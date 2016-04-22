package com.advance.java.server.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Productrepairwork {
    private int repairWorkId;
    private int orderId;
    private int productSn;
    private int createdStaff;
    private Integer assignedTechnician;
    private Timestamp createdDate;
    private Timestamp solvedDate;

    @Id
    @Column(name = "RepairWorkId", nullable = false)
    public int getRepairWorkId() {
        return repairWorkId;
    }

    public void setRepairWorkId(int repairWorkId) {
        this.repairWorkId = repairWorkId;
    }

    @Basic
    @Column(name = "OrderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "ProductSn", nullable = false)
    public int getProductSn() {
        return productSn;
    }

    public void setProductSn(int productSn) {
        this.productSn = productSn;
    }

    @Basic
    @Column(name = "CreatedStaff", nullable = false)
    public int getCreatedStaff() {
        return createdStaff;
    }

    public void setCreatedStaff(int createdStaff) {
        this.createdStaff = createdStaff;
    }

    @Basic
    @Column(name = "AssignedTechnician", nullable = true)
    public Integer getAssignedTechnician() {
        return assignedTechnician;
    }

    public void setAssignedTechnician(Integer assignedTechnician) {
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
        if (orderId != that.orderId) return false;
        if (productSn != that.productSn) return false;
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
        result = 31 * result + orderId;
        result = 31 * result + productSn;
        result = 31 * result + createdStaff;
        result = 31 * result + (assignedTechnician != null ? assignedTechnician.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (solvedDate != null ? solvedDate.hashCode() : 0);
        return result;
    }
}
