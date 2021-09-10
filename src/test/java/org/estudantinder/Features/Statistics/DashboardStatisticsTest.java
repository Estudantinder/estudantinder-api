package org.estudantinder.Features.Statistics;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DashboardStatisticsTest {

    @Test
    public void testDashboardStatisticsEndpoint() {
        given()
        .when().get("/statistics/dashboard")
        .then()
            .statusCode(200);
    }

}
