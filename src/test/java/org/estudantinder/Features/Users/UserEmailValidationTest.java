package org.estudantinder.Features.Users;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import javax.json.Json;

@QuarkusTest
public class UserEmailValidationTest {

    @Test
    public void testEmailValidationEndpoint() {
        
        String testEmail = Json.createObjectBuilder()
            .add("email", "asdfjhasodjfisojadfiojasdfioasdf@email.com")
        .build().toString();

        given()
        .body(testEmail)
        .contentType(ContentType.JSON)
        .when().post("/users/emails/available")
        .then()
            .statusCode(204);
    }

    @Test
    public void testConflictEmailValidationEndpoint() {
        
        String testEmail = Json.createObjectBuilder()
            .add("email", "test4@email.com")
        .build().toString();

        given()
        .body(testEmail)
        .contentType(ContentType.JSON)
        .when().post("/users/emails/available")
        .then()
            .statusCode(409);
    }

}