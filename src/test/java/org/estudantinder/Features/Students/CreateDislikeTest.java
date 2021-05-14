package org.estudantinder.Features.Students;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class CreateDislikeTest {

    @Test
    public void testCreateDislikeEndpoint() {
        given()
        .pathParam("id", 24)
        .auth().oauth2(generateValidStudentToken())
        .when().post("/students/dislikes/{id}")
        .then()
            .statusCode(201);
    }

    @Test
    public void testConflictCreateDislikeEndpoint() {
        given()
        .pathParam("id", 24)
        .auth().oauth2(generateValidStudentToken())
        .when().post("/students/dislikes/{id}")
        .then()
            .statusCode(409);
    }

    @Test
    public void testNotFoundCreateDislikeEndpoint() {
        given()
        .pathParam("id", -24)
        .auth().oauth2(generateValidStudentToken())
        .when().post("/students/dislikes/{id}")
        .then()
            .statusCode(404);
    }
    
    @Test
    public void testBadRequestCreateDislikeEndpoint() {
        given()
        .pathParam("id", 22)
        .auth().oauth2(generateValidStudentToken())
        .when().post("/students/dislikes/{id}")
        .then()
            .statusCode(400);
    }

    static String generateValidStudentToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", 22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign();
    }
    
}
