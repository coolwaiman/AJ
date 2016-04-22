package com.advance.java.server;

import com.advance.java.server.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by rAYMOND on 4/22/2016.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration conf = new Configuration()
                    .addAnnotatedClass(Position.class)
                    .addAnnotatedClass(Productrepairwork.class)
                    .addAnnotatedClass(Storestockwarning.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Provider.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Store.class)
                    .addAnnotatedClass(Storeproduct.class)
                    .addAnnotatedClass(Staff.class)
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Cusorder.class)
                    .addAnnotatedClass(Orderline.class)
                    .addAnnotatedClass(Customer.class)
                    .configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            return conf.buildSessionFactory(sr);
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}