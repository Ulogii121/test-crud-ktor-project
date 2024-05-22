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
        UserModelTable userFromDb = session.find(UserModelTable.class, 1);

        Assertions.assertEquals(userFromService.getLogin(), userFromDb.getLogin());
        Assertions.assertEquals(userFromService.getFirstName(), userFromDb.getFirstName());
        Assertions.assertEquals(userFromService.getSurname(), userFromDb.getSurname(), "surname");
        Assertions.assertEquals(userFromService.getPatronymic(), userFromDb.getPatronymic());
    }

    @Test
    void postUser() {
        User userRequest = new User();
        userRequest.setLogin("n.mayurov");
        userRequest.setFirstName("Nikita");
        userRequest.setSurname("Mayurov");
        userRequest.setPatronymic("Alekseevich");

        User userFromService = userService.postUser(userRequest);
        UserModelTable userFromDb = session.find(UserModelTable.class, userFromService.getId());

        Assertions.assertEquals(userFromService.getLogin(), userFromDb.getLogin());
        Assertions.assertEquals(userFromService.getFirstName(), userFromDb.getFirstName());
        Assertions.assertEquals(userFromService.getSurname(), userFromDb.getSurname(), "surname");
        Assertions.assertEquals(userFromService.getPatronymic(), userFromDb.getPatronymic());
    }

    @Test
    void deleteUser() {
        User userEntity = new User();
        userEntity.setId(3000);
        userEntity.setLogin("n.mayurov");
        userEntity.setFirstName("Nikita");
        userEntity.setSurname("Mayurov");
        userEntity.setPatronymic("Alekseevich");
    }

    @AfterAll
    static void closeConnection() {
        session.close();
    }
}
