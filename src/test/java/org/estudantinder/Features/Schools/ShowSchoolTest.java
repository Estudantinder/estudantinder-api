package org.estudantinder.Features.Schools;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class ShowSchoolTest {

    @Test
    public void testIndexSchoolEndpoint() {
        
        given()
        .when().get("/schools")
        .then()
            .statusCode(200);
    }
    
    @Test
    public void testShowSchoolEndpoint() {
        given()
        .pathParam("id", 2)
        .when().get("/schools/{id}")
        .then()
            .statusCode(200);
        
    }

    @Test
    public void testNotFoundShowSchoolEndpoint() {
        given()
        .pathParam("id", -2)
        .when().get("/schools/{id}")
        .then()
            .statusCode(404);
        
    }
}
