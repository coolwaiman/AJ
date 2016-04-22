package com.advance.java.server;

import com.advance.java.server.model.Category;
import com.advance.java.server.model.Product;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class Test {
    public static void main(String... args) {
        //insertCategory();
        insertProvider();
        insertGame();
    }

    private static void insertGame() {
    }

    private static void insertProvider() {
    }

    public static void insertCategory() {
        String[] cate = new String[]{
            "Game", "Console"
        };
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        for(String c : cate) {
            Category category = new Category();
            category.setCategoryName(c);
            s.save(category);
        }
        s.getTransaction().commit();
    }


}
