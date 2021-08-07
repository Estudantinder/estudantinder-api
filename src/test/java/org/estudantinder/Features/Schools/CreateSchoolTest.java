package org.estudantinder.Features.Schools;

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
public class CreateSchoolTest {
    
    @Test
    public void testCreateSchoolEndpoint() {

        String testSchool = Json.createObjectBuilder()
            .add("name", "TEST SCHOOL")
            .add("address", "TEST ADDRESS")
            .add("courses", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("name", "TEST COURSE 1"))
                .add(Json.createObjectBuilder()
                    .add("name", "TEST COURSE 2")))
            .build().toString();

        given()
            .body(testSchool)
            .contentType(ContentType.JSON)
            .auth().oauth2(generateValidUserToken())
            .when().post("/schools")
            .then()
                .statusCode(201)
                .body("name", containsString("TEST SCHOOL"));
    }

    @Test
    public void testConflictCreateSchoolEndpoint() {

        String testSchool = Json.createObjectBuilder()
            .add("name", "TEST SCHOOL")
            .add("address", "TEST ADDRESS")
            .add("courses", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("name", "TEST COURSE 1"))
                .add(Json.createObjectBuilder()
                    .add("name", "TEST COURSE 2")))
            .build().toString();

        given()
            .body(testSchool)
            .contentType(ContentType.JSON)
            .auth().oauth2(generateValidUserToken())
            .when().post("/schools")
            .then()
                .statusCode(409);
    }


    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
