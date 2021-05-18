package org.estudantinder.Features.Students;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class ShowMatchsTest {

    @Test
    public void testShowMatchsEndpoint() {
        given()
        .auth().oauth2(generateValidStudentToken())
        .when().get("/students/matchs")
        .then()
            .statusCode(200);
    }
    @Test
    public void testNotFoundShowMatchsEndpoint() {
        given()
        .auth().oauth2(generateNonExistentStudentToken())
        .when().get("/students/matchs")
        .then()
            .statusCode(404);
    }

    static String generateValidStudentToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", 22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign();
    }

    static String generateNonExistentStudentToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", -22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign();
    }
}