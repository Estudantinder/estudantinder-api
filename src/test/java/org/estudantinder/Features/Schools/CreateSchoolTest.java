package org.estudantinder.Features.Schools;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.containsString;

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
            .when().post("/schools")
            .then()
                .statusCode(201)
                .body("name", containsString("TEST SCHOOL"));
    }
}
