package org.estudantinder.Features.Users;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class ShowUserFilters {

    @Test
    public void testEditUserFiltersEndpoint() {
        given()
        .auth().oauth2(generateValidStudentToken())
        .contentType(ContentType.JSON)
        .when().get("/users/filters")
        .then()
            .statusCode(200);
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