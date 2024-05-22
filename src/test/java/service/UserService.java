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

    public User postUser(User request) {
        return RestAssured
                .given()
                .spec(specUsers)
                .body(request)
                .post("user")
                .then()
                .statusCode(201)
                .extract()
                .as(User.class);
    }

    public User[] getUsers(String searchQuery, Integer limit) {
        return RestAssured
                .given()
                .spec(specUsers)
                .queryParam("searchQuery", searchQuery)
                .queryParam("limit", limit)
                .get("users")
                .then()
                .statusCode(200)
                .extract()
                .as(User[].class);
    }
}
