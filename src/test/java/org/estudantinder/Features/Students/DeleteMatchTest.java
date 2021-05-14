package org.estudantinder.Features.Students;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class DeleteMatchTest {

    @Test
    public void testDeleteMatchEndpoint() {
        given()
        .auth().oauth2(generateValidStudentToken())
        .pathParam("id", 32)
        .when().delete("/students/matchs/{id}")
        .then()
            .statusCode(200);
    }

    @Test
    public void testNotFoundDeleteMatchEndpoint() {
        given()
        .auth().oauth2(generateValidStudentToken())
        .pathParam("id", -32)
        .when().delete("/students/matchs/{id}")
        .then()
            .statusCode(404);
    }

    @Test
    public void testUnauthorizedDeleteMatchEndpoint() {
        // can't delete a match that isn't yours
        given()
        .auth().oauth2(generateValidStudentToken())
        .pathParam("id", 31)
        .when().delete("/students/matchs/{id}")
        .then()
            .statusCode(401);
    }

    static String generateValidStudentToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", 24)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign();
    }
}