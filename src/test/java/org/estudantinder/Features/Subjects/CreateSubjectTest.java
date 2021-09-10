package org.estudantinder.Features.Subjects;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateSubjectTest {
    
    @Test
    public void testCreateSubjectEndpoint() {

        given()
            .multiPart("name", "TEST SUBJECT")
            .multiPart("photo", new File("/home/adamaugustinsky/Documents/estudantinder-api/src/test/resources/subject.png"))
            .auth().oauth2(generateValidUserToken())
            .when().post("/subjects")
            .then()
                .statusCode(201);
    }

    @Test
    public void testConflictCreateSubjectEndpoint() {

        given()
            .multiPart("name", "TEST SUBJECT")
            .multiPart("photo", new File("/home/adamaugustinsky/Documents/estudantinder-api/src/test/resources/subject.png"))
            .auth().oauth2(generateValidUserToken())
            .when().post("/subjects")
            .then()
                .statusCode(409);
    }

    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
