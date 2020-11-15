package org.estudantinder.Features.School;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteSchoolTest {
    
    @Test
    public void testDeleteSchoolEndpoint() {
        given()
            .pathParam("id", 1)
            .when().delete("/school/{id}")
            .then()
                .statusCode(200);
    }
}
