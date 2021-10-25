package org.estudantinder.Features.Admins;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;


import static io.restassured.RestAssured.given;

@QuarkusTest
public class BanReportedUserTest {

    @Test
    public void testBanReportedUserEndpoint() {

        given()
            .pathParam("userId", 33)
            .auth().oauth2(generateValidUserToken())
            .when().delete("/admins/users/{userId}")
            .then()
                .statusCode(200);
    }

    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
