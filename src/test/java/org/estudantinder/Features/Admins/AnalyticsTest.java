package org.estudantinder.Features.Admins;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@QuarkusTest
public class AnalyticsTest {

    @Test
    public void testSubjectStatisticsEndpoint() {
        given()
        .auth().oauth2(generateValidAdminToken())
        .when().get("/admins/analytics")
        .then()
            .statusCode(200);
    }


    static String generateValidAdminToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
