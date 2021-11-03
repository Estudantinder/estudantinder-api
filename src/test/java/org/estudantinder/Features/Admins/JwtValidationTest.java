package org.estudantinder.Features.Admins;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class JwtValidationTest {

    @Test
    public void testJwtValidationEndpoint() {
        given()
        .auth().oauth2(generateValidAdminToken())
        .when().post("/admins/me/session")
        .then()
            .statusCode(204);
    }

    @Test
    public void testNotFoundJwtValidationEndpoint() {
        given()
        .auth().oauth2(generateNonExistentAdminToken())
        .when().post("/admins/me/session")
        .then()
            .statusCode(404);
    }

    static String generateValidAdminToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("Admin")
            .claim("id", 37)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }

    static String generateNonExistentAdminToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("Admin")
            .claim("id", -37)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }
}
