package com.advance.java.server.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Position implements Comparable<Position> {
    private int positionId;
    private String positionName;
    private List<Staff> staffs;


    @OneToMany(mappedBy = "position")
    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PositionId", nullable = false)
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "PositionName", nullable = false, length = 200)
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (positionId != position.positionId) return false;
        if (positionName != null ? !positionName.equals(position.positionName) : position.positionName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = positionId;
        result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Position o) {
        return positionId - o.positionId;
    }
}
