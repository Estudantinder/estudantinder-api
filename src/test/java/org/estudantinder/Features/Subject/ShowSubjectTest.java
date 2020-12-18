package org.estudantinder.Features.Subject;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class ShowSubjectTest {

    @Test
    public void testIndexSubjectEndpoint() {
        
        given()
        .when().get("/subject")
        .then()
            .statusCode(200);
    }
    
    @Test
    public void testShowSubjectEndpoint() {
        given()
        .pathParam("id", 11)
        .when().get("/subject/{id}")
        .then()
            .statusCode(200);
        
    }
}
