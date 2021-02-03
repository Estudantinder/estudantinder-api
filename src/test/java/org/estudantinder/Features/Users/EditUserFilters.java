package org.estudantinder.Features.Users;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.json.Json;

@QuarkusTest
public class EditUserFilters {

    @Test
    public void testEditUserFiltersEndpoint() {

        String testFilters = Json.createObjectBuilder()
        .add("preferences", Json.createObjectBuilder()
            .add("school_year", 3)
            .add("shift", 1)
            .add("course_id", 5))
            .build().toString();

        given()
        .auth().oauth2(generateValidStudentToken())
        .body(testFilters)
        .contentType(ContentType.JSON)
        .when().put("/users/filters")
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