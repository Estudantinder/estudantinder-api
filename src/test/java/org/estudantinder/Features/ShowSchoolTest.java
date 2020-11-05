package org.estudantinder.Features;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class ShowSchoolTest {

    @Test
    public void testIndexSchoolEndpoint() {
        
        given()
        .when().get("/school")
        .then()
            .statusCode(200);
    }
    
    @Test
    public void testShowSchoolEndpoint() {
        given()
        .pathParam("id", -2)
        .when().get("/school/{id}")
        .then()
            .statusCode(200);
        
    }
}
