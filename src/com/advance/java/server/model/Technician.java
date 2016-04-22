package com.advance.java.server.model;

import javax.persistence.*;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Technician {
    private int technicianId;
    private String technicianName;
    private String technicianContact;
    private Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TechnicianId", nullable = false)
    public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    @Basic
    @Column(name = "TechnicianName", nullable = false, length = 255)
    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    @Basic
    @Column(name = "TechnicianContact", nullable = false, length = 30)
    public String getTechnicianContact() {
        return technicianContact;
    }

    public void setTechnicianContact(String technicianContact) {
        this.technicianContact = technicianContact;
    }

    @OneToOne
    @JoinColumn(name = "AccountId", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account accountId) {
        this.account = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Technician that = (Technician) o;

        if (technicianId != that.technicianId) return false;
        if (account != that.account) return false;
        if (technicianName != null ? !technicianName.equals(that.technicianName) : that.technicianName != null)
            return false;
        if (technicianContact != null ? !technicianContact.equals(that.technicianContact) : that.technicianContact != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = technicianId;
        result = 31 * result + (technicianName != null ? technicianName.hashCode() : 0);
        result = 31 * result + (technicianContact != null ? technicianContact.hashCode() : 0);
        result = 31 * result + account.hashCode();
        return result;
    }
}
