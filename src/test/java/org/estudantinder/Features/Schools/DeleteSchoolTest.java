package org.estudantinder.Features.Schools;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteSchoolTest {
    
    @Test
    public void testDeleteSchoolEndpoint() {
        given()
            .pathParam("id", 3)
            .when().delete("/schools/{id}")
            .then()
                .statusCode(200);
    }
}
