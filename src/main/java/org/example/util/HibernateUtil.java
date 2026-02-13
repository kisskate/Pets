package org.example.util;
import org.example.relationships.Owner;
import org.example.relationships.Pet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(String jdbcUrl, String username, String password) {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", jdbcUrl);
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.addAnnotatedClass(Pet.class);
            configuration.addAnnotatedClass(Owner.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
