package org.estudantinder.Features.Users;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class CreateLikeTest {

    @Test
    public void testCreateLikeEndpoint() {
        given()
        .pathParam("id", 24)
        .auth().oauth2(generateValidStudentToken())
        .when().post("/users/likes/{id}")
        .then()
            .statusCode(201);
    }

    static String generateValidStudentToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("Student")
            .claim("id", 22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign();
    }
    
}
