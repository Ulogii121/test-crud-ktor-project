package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import objects.UserObject;
import org.apache.http.HttpStatus;

public class UserApi {
    private RequestSpecification specUser =
            RestAssured
                    .given()
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .contentType(ContentType.JSON)
                    .baseUri("http://localhost:8080")
                    .basePath("/test-crud-ktor-project/");

    public UserObject getUserById(int id) {
        return RestAssured
                .given()
                .spec(specUser)
                .get("user/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(UserObject.class);
    }
}
