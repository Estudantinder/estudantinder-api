package org.estudantinder.Features.Users;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

import javax.json.Json;


@QuarkusTest
public class AuthenticateUserTest {

    @Test
    public void testAuthenticateUserEndpoint() {

        String testUser = Json.createObjectBuilder()
        .add("email", "test4@email.com")
        .add("password", "TEST PASSWORD 4")
        .build().toString();
        
        given()
            .body(testUser)
            .contentType(ContentType.JSON)
            .when().post("/users/session")
            .then()
                .statusCode(200)
                .body(containsString("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9"));
    }

    @Test
    public void testNotFoundAuthenticateUserEndpoint() {

        String testUser = Json.createObjectBuilder()
        .add("email", "asdufhasdfiuhasduf@email.com")
        .add("password", "TEST PASSWORD 4")
        .build().toString();
        
        given()
            .body(testUser)
            .contentType(ContentType.JSON)
            .when().post("/users/session")
            .then()
                .statusCode(404);
    }

    @Test
    public void testUnauthorizedAuthenticateUserEndpoint() {
        String testUser = Json.createObjectBuilder()
        .add("email", "test4@email.com")
        .add("password", "sadfjoaisdfjasiodf")
        .build().toString();
        
        given()
            .body(testUser)
            .contentType(ContentType.JSON)
            .when().post("/users/session")
            .then()
                .statusCode(401);
    }
}
