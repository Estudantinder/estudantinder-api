package org.estudantinder.Features.Statistics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FilterUsageTest {

    @Test
    public void testFilterUsageEndpoint() {
        given()
        .when().get("/statistics/filterUsage")
        .then()
            .statusCode(200);
    }
}
