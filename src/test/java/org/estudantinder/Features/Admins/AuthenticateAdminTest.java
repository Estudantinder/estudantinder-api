package org.estudantinder.Features.Admins;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

import javax.json.Json;


@QuarkusTest
public class AuthenticateAdminTest {

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
}
