package org.example.Program;

import org.example.Program.DataInit;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory(
                    "jdbc:postgresql://localhost:7432/lab2new",
                    "postgres",
                    "password"
            );

            DataInit.initialize(sessionFactory);

            System.out.println("Database completed successfully!)");

        } catch (Exception e) {
            System.err.println("Initialization failed:");
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}