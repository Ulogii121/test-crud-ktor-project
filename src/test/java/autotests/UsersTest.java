package autotests;

import api.UserApi;
import database.DatabaseConnector;
import database.UserTable;
import objects.UserObject;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UsersTest {
    static Session session;
    static UserApi userApi;

    @BeforeAll
    static void getConnection() {
        session = DatabaseConnector.getSessionFactory().openSession();
        userApi = new UserApi();
    }

    @Test
    public void testGetUser() {
        int userId = 18;

        UserObject userFromService = userApi.getUserById(userId);

        UserTable userFromDb = session.find(UserTable.class, userId);

        Assertions.assertEquals(userFromService.getLogin(), userFromDb.getLogin(), "Поле login");
        Assertions.assertEquals(userFromService.getSurname(), userFromDb.getSurname());
        Assertions.assertEquals(userFromService.getFirstName(), userFromDb.getFirstName());
        Assertions.assertEquals(userFromService.getPatronymic(), userFromDb.getPatronymic());
    }

    @Test

    @AfterAll
    static void closeConnection() {
        session.close();
    }
}
