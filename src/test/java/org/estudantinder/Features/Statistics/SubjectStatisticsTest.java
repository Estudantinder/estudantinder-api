package org.estudantinder.Features.Statistics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SubjectStatisticsTest {

    @Test
    public void testSubjectStatisticsEndpoint() {
        given()
        .when().get("/statistics/subjects")
        .then()
            .statusCode(200);
    }
}
