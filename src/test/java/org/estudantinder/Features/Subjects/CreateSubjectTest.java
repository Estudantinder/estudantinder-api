package org.estudantinder.Features.Subjects;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import static org.hamcrest.Matchers.containsString;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import javax.json.Json;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateSubjectTest {
    
    @Test
    public void testCreateSubjectEndpoint() {

        String testSubject = Json.createObjectBuilder()
            .add("name", "TEST SUBJECT")
            .build().toString();

        given()
            .body(testSubject)
            .contentType(ContentType.JSON)
            .auth().oauth2(generateValidUserToken())
            .when().post("/subjects")
            .then()
                .statusCode(201)
                .body("name", containsString("TEST SUBJECT"));
    }

    @Test
    public void testConflictCreateSubjectEndpoint() {

        String testSubject = Json.createObjectBuilder()
            .add("name", "TEST SUBJECT")
            .build().toString();

        given()
            .body(testSubject)
            .contentType(ContentType.JSON)
            .auth().oauth2(generateValidUserToken())
            .when().post("/subjects")
            .then()
                .statusCode(409);
    }

    @Test
    public void testNotFoundCreateSubjectEndpoint() {
        given()
            .contentType(ContentType.JSON)
            .auth().oauth2(generateValidUserToken())
            .when().post("/subjects")
            .then()
                .statusCode(400);
    }


    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
