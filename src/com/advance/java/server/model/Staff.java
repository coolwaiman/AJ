package com.advance.java.server.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Staff {
    private int staffId;
    private String staffName;
    private String staffPhone;
    private int staffAccountId;

    @Id
    @Column(name = "StaffId", nullable = false)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "StaffName", nullable = false, length = 80)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "StaffPhone", nullable = true, length = 30)
    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    @Basic
    @Column(name = "StaffAccountId", nullable = false)
    public int getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(int staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (staffId != staff.staffId) return false;
        if (staffAccountId != staff.staffAccountId) return false;
        if (staffName != null ? !staffName.equals(staff.staffName) : staff.staffName != null) return false;
        if (staffPhone != null ? !staffPhone.equals(staff.staffPhone) : staff.staffPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staffId;
        result = 31 * result + (staffName != null ? staffName.hashCode() : 0);
        result = 31 * result + (staffPhone != null ? staffPhone.hashCode() : 0);
        result = 31 * result + staffAccountId;
        return result;
    }
}
