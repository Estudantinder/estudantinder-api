package org.estudantinder.Features.Subjects;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

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
            .when().put("/subjects/{id}")
            .then()
                .statusCode(404);
    }
}
