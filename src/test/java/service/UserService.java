package service;

import dto.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UserService {

    private final RequestSpecification specUsers =
            RestAssured
                    .given()
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .contentType(ContentType.JSON)
                    .baseUri("http://localhost:8080")
                    .basePath("/test-crud-ktor-project/");

    public User getUser(int id) {
        return RestAssured
                .given()
                .spec(specUsers)
                .get("user/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);
    }
}
