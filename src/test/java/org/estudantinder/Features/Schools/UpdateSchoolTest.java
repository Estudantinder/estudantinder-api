package org.estudantinder.Features.Schools;

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
public class UpdateSchoolTest {
    
    @Test
    public void testUpdateSchoolEndpoint() {
        
        String updatedTestSchool = Json.createObjectBuilder()
            .add("courses", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("name", "UPDATED TEST COURSE 1"))
                .add(Json.createObjectBuilder()
                    .add("name", "UPDATED TEST COURSE 2"))
            ).build().toString();

        given()
            .body(updatedTestSchool)
            .pathParam("id", 1)
            .auth().oauth2(generateValidUserToken())
            .contentType(ContentType.JSON)
            .when().put("/schools/{id}")
            .then()
                .statusCode(200);
    }

    @Test
    public void testNotFoundUpdateSchoolEndpoint() {
        
        String updatedTestSchool = Json.createObjectBuilder()
            .add("courses", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("name", "UPDATED TEST COURSE 1"))
                .add(Json.createObjectBuilder()
                    .add("name", "UPDATED TEST COURSE 2"))
            ).build().toString();

        given()
            .body(updatedTestSchool)
            .pathParam("id", 9)
            .auth().oauth2(generateValidUserToken())
            .contentType(ContentType.JSON)
            .when().put("/schools/{id}")
            .then()
                .statusCode(404);
    }


    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
