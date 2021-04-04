package org.estudantinder.Features.Users;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static io.restassured.RestAssured.given;

import javax.json.Json;


@QuarkusTest
public class UpdateUserTest {
    
    @Test
    public void testUpdateStudentEndpoint() {
        
        String testStudentUpdated = Json.createObjectBuilder()
            .add("name", "TEST STUDENT UPDATED")
            .add("shift", 2)
            .add("photos", Json.createArrayBuilder()
                .add("http://testPhoto6.com"))
            .add("subjects_ids", Json.createArrayBuilder()
                .add(10))
            .add("contacts", Json.createObjectBuilder()
            .add("whatsapp", 551112345))
            .build().toString();
        
        given()
            .auth().oauth2(generateValidStudentToken())
            .body(testStudentUpdated)
            .contentType(ContentType.JSON)
            .when().put("/users")
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
