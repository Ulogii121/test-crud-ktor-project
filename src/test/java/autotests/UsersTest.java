package autotests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import objects.UserObject;
import org.junit.jupiter.api.Test;

public class UsersTest {
    private RequestSpecification specUser =
            RestAssured
                    .given()
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .contentType(ContentType.JSON)
                    .baseUri("http://localhost:8080")
                    .basePath("/test-crud-ktor-project/");

    @Test
    public void testGetUser() {
        UserObject userFromService = RestAssured
                .given()
                .spec(specUser)
                .get("user/18")
                .then()
                .statusCode(200)
                .extract()
                .as(UserObject.class);
    }
}
