package org.estudantinder.Features.Statistics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class NumberOfUsersTest {

    @Test
    public void testShowNumberOfUsersEndpoint() {
        given()
        .when().get("/statistics/numberOfUsers")
        .then()
            .statusCode(200);
    }

}