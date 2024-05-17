package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnector {

    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/users")
                .setProperty("hibernate.connection.username", "admin")
                .setProperty("hibernate.connection.password", "password")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.show_sql", "true")
                .addAnnotatedClass(UserModelTable.class)
                .buildSessionFactory();
    }
}
