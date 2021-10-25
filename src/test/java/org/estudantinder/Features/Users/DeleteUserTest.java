package org.estudantinder.Features.Users;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@QuarkusTest
public class DeleteUserTest {

    @Test
    public void testDeleteUserEndpoint() {

        given()
            .auth().oauth2(generateValidUserToken())
            .when().delete("/users/me")
            .then()
                .statusCode(200);
    }

    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("User")).claim("id", 35).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
