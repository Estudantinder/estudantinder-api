package org.estudantinder.Features.Admins;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.json.Json;


@QuarkusTest
public class CreateAdminTest {

    @Test
    public void testCreateAdminEndpoint() {

        String testAdmin = Json.createObjectBuilder()
        .add("name", "TEST ADMIN 2")
        .add("email", "test2@email.com")
        .add("password", "TEST ADMIN 2")
            .build().toString();
        
        given()
            .body(testAdmin)
            .auth().oauth2(generateValidAdminToken())
            .contentType(ContentType.JSON)
            .when().post("/admins")
            .then()
                .statusCode(201);
    }

    @Test
    public void testConflictCreateAdminEndpoint() {

        String testAdmin = Json.createObjectBuilder()
        .add("name", "TEST ADMIN 1")
        .add("email", "test1@email.com")
        .add("password", "TEST ADMIN 1")
            .build().toString();
        
        given()
            .body(testAdmin)
            .auth().oauth2(generateValidAdminToken())
            .contentType(ContentType.JSON)
            .when().post("/admins")
            .then()
                .statusCode(409);
    }

    @Test
    public void testBadRequestConflictCreateAdminEndpoint() {
        String testAdmin = Json.createObjectBuilder()
        .add("name", "TEST ADMIN 4")
        .add("email", "test4@email.com")
        .add("password", "TestPassword")
            .build().toString();
        
        given()
            .body(testAdmin)
            .auth().oauth2(generateValidAdminToken())
            .contentType(ContentType.JSON)
            .when().post("/admins")
            .then()
                .statusCode(400);
    }


    static String generateValidAdminToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("Admin")
            .claim("id", 37)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }
}
