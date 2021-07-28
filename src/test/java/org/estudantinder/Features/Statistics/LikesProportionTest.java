package org.estudantinder.Features.Statistics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class LikesProportionTest {

    @Test
    public void testLikesProportionEndpoint() {
        given()
        .pathParam("id", 22)
        .when().get("/statistics/likesProportion/{id}")
        .then()
            .statusCode(200);
    }
}
