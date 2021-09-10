package org.estudantinder.Features.Admins;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@QuarkusTest
public class ShowFullUserTest {

    @Test
    public void testShowFullUserEndpoint() {
        given()
            .pathParam("userId", 35)
            .auth().oauth2(generateValidUserToken())
            .when().get("/admins/showFullUser/{userId}")
            .then()
                .statusCode(200);
    }

    @Test
    public void testNotFoundShowUserEndpoint() {
        given()
            .pathParam("userId", -35)
            .auth().oauth2(generateValidUserToken())
            .when().get("/admins/showFullUser/{userId}")
            .then()
                .statusCode(404);
    }

    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("Admin")
            .claim("id", 37)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }

}