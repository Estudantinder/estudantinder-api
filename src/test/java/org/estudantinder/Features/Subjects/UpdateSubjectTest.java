package org.estudantinder.Features.Subjects;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@QuarkusTest
public class UpdateSubjectTest {
    
    @Test
    public void testUpdateSchoolEndpoint() {

        given()
            .multiPart("name", "UPDATED TEST SUBJECT")
            .multiPart("photo", new File("/home/adamaugustinsky/Documents/estudantinder-api/src/test/resources/subject.png"))
            .pathParam("id", 12)
            .auth().oauth2(generateValidUserToken())
            .when().put("/subjects/{id}")
            .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testNotFoundUpdateSchoolEndpoint() {
        
        given()
            .multiPart("name", "UPDATED TEST SUBJECT")
            .multiPart("photo", new File("/home/adamaugustinsky/Documents/estudantinder-api/src/test/resources/subject.png"))
            .pathParam("id", -12)
            .auth().oauth2(generateValidUserToken())
            .when().put("/subjects/{id}")
            .then()
                .statusCode(404);
    }


    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
