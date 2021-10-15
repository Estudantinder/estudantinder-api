package org.estudantinder.Features.Admins;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

import javax.json.Json;


@QuarkusTest
public class AuthenticateUserTest {

    @Test
    public void testAuthenticateAdminEndpoint() {

        String testAdmin = Json.createObjectBuilder()
        .add("email", "test1@email.com")
        .add("password", "TEST ADMIN 1")
        .build().toString();
        
        given()
            .body(testAdmin)
            .contentType(ContentType.JSON)
            .when().post("/admins/session")
            .then()
                .statusCode(200)
                .body(containsString("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9"));
    }

    @Test
    public void testNotFoundAuthenticateUserEndpoint() {

        String testAdmin = Json.createObjectBuilder()
        .add("email", "asdufhasdfiuhasduf@email.com")
        .add("password", "TEST PASSWORD 4")
        .build().toString();
        
        given()
            .body(testAdmin)
            .contentType(ContentType.JSON)
            .when().post("/users/login")
            .then()
                .statusCode(404);
    }

    @Test
    public void testUnauthorizedAuthenticateUserEndpoint() {

        String testAdmin = Json.createObjectBuilder()
        .add("email", "test4@email.com")
        .add("password", "sadfjoaisdfjasiodf")
        .build().toString();
        
        given()
            .body(testAdmin)
            .contentType(ContentType.JSON)
            .when().post("/users/login")
            .then()
                .statusCode(401);
    }
}
