package org.estudantinder.Features.Subjects;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@QuarkusTest
public class DeleteSubjectTest {
    
    @Test
    public void testDeleteSubjectEndpoint() {
        given()
            .pathParam("id", 13)
            .auth().oauth2(generateValidUserToken())
            .when().delete("/subjects/{id}")
            .then()
                .statusCode(200);
    }

    @Test
    public void testNotFoundDeleteSubjectEndpoint() {
        given()
            .pathParam("id", -13)
            .auth().oauth2(generateValidUserToken())
            .when().delete("/subjects/{id}")
            .then()
                .statusCode(404);
    }


    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
