package com.advance.java.server.dao;

import com.advance.java.server.model.Position;

import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class PositionDAO extends DAO{
    public static List<Position> getAll() {
        return getSession().createQuery("from Position").list();
    }

    public static Position getByName(String name) {
        return (Position) getSession().createQuery("from Position where positionName = :name")
                .setString("name", name)
                .uniqueResult();
    }
}
