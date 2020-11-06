package org.estudantinder.Features.School;

import org.estudantinder.entities.School;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateSchoolTest {
    
    @Test
    public void testCreateSchoolEndpoint() {
        School testSchool = new School();

        testSchool.setName("TEST SCHOOL");
        testSchool.setAddress("ADDRESS");

        given()
            .body(testSchool)
            .contentType(ContentType.JSON)
            .when().post("/school")
            .then()
                .statusCode(201)
                .body("name", containsString(testSchool.getName()));
    }
}
