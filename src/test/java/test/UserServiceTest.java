package test;

import database.DatabaseConnector;
import database.UserModelTable;
import dto.User;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.UserService;

public class UserServiceTest {
    static Session session;
    static UserService userService = new UserService();

    @BeforeAll
    static void setConnection() {
        session = DatabaseConnector.getSessionFactory().openSession();
    }

    @Test
    void getUser() {
        User userFromService = userService.getUser(1);
        UserModelTable userFromDB = session.find(UserModelTable.class, 1);

        Assertions.assertEquals(userFromService.getLogin(), userFromDB.getLogin());
        Assertions.assertEquals(userFromService.getFirstName(), userFromDB.getFirstName());
        Assertions.assertEquals(userFromService.getSurname(), userFromDB.getSurname(), "surname");
        Assertions.assertEquals(userFromService.getPatronymic(), userFromDB.getPatronymic());
    }

    @AfterAll
    static void closeConnection() {
        session.close();
    }
}
