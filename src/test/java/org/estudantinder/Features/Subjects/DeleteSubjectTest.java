package org.estudantinder.Features.Subjects;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteSubjectTest {
    
    @Test
    public void testDeleteSubjectEndpoint() {
        given()
            .pathParam("id", 13)
            .when().delete("/subjects/{id}")
            .then()
                .statusCode(200);
    }

    @Test
    public void testNotFoundDeleteSubjectEndpoint() {
        given()
            .pathParam("id", -13)
            .when().delete("/subjects/{id}")
            .then()
                .statusCode(404);
    }
}
