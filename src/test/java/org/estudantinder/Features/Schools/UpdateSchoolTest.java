package org.estudantinder.Features.Schools;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

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
            .contentType(ContentType.JSON)
            .when().put("/schools/{id}")
            .then()
                .statusCode(404);
    }
}
