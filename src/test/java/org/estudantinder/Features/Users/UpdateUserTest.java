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
    public void testUpdateUserEndpoint() {
        
        String testUserUpdated = Json.createObjectBuilder()
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
            .auth().oauth2(generateValidUserToken())
            .body(testUserUpdated)
            .contentType(ContentType.JSON)
            .when().put("/users/me")
            .then()
                .statusCode(200);
    }

    @Test
    public void testNotFoundUpdateUserEndpoint() {
        
        String testUserUpdated = Json.createObjectBuilder()
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
            .auth().oauth2(generateNonExistentUserToken())
            .body(testUserUpdated)
            .contentType(ContentType.JSON)
            .when().put("/users/me")
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
