package com.advance.java.server;

import com.advance.java.server.model.Category;
import com.advance.java.server.model.Product;
import org.hibernate.Session;
import java.util.PropertyResourceBundle;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

class Test {

    class Point {

    }

    public static void main(String... args) throws ExecutionException, InterruptedException, IOException {

    }



    private void insertGame() throws ExecutionException, InterruptedException {
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
