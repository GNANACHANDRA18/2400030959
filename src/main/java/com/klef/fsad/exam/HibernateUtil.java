package com.klef.fsad.exam;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility Class for managing SessionFactory
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory using Configuration
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Department.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Get the SessionFactory instance
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Shutdown the SessionFactory
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
