package org.estudantinder.Features.Subjects;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import javax.json.Json;

@QuarkusTest
public class UpdateSubjectTest {
    
    @Test
    public void testUpdateSchoolEndpoint() {
        
        String updatedTestSchool = Json.createObjectBuilder()
            .add("name", "UPDATED TEST SUBJECT")
            .build().toString();

        given()
            .body(updatedTestSchool)
            .pathParam("id", 12)
            .contentType(ContentType.JSON)
            .auth().oauth2(generateValidUserToken())
            .when().put("/subjects/{id}")
            .then()
                .statusCode(200);
    }

    @Test
    public void testNotFoundUpdateSchoolEndpoint() {
        
        String updatedTestSchool = Json.createObjectBuilder()
            .add("name", "UPDATED TEST SUBJECT")
            .build().toString();

        given()
            .body(updatedTestSchool)
            .pathParam("id", -12)
            .contentType(ContentType.JSON)
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
