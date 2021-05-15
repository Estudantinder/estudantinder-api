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
public class EditUserFiltersTest {

    @Test
    public void testEditUserFiltersEndpoint() {

        String testFilters = Json.createObjectBuilder()
            .add("school_year", 3)
            .add("shift", 1)
            .add("course_id", 5)
            .add("subjects_ids", Json.createArrayBuilder()
                .add(10)
                .add(11)
                .add(12))
            .build().toString();

        given()
        .auth().oauth2(generateValidUserToken())
        .body(testFilters)
        .contentType(ContentType.JSON)
        .when().put("/users/filters")
        .then()
            .statusCode(200);
    }

    @Test
    public void testNotFoundEditUserFiltersEndpoint() {

        String testFilters = Json.createObjectBuilder()
            .add("school_year", 3)
            .add("shift", 1)
            .add("course_id", 5)
            .add("subjects_ids", Json.createArrayBuilder()
                .add(10)
                .add(11)
                .add(12))
            .build().toString();

        given()
        .auth().oauth2(generateNonExistentUserToken())
        .body(testFilters)
        .contentType(ContentType.JSON)
        .when().put("/users/filters")
        .then()
            .statusCode(404);
    }

    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", 22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }

    static String generateNonExistentUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", -22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }
}