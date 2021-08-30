package org.estudantinder.Features.Reports;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import javax.json.Json;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateReportTest {

    @Test
    public void testCreateReportEndpoint() {

        String testReport = Json.createObjectBuilder().add("type", "fakeProfile")
                .build().toString();

        given()
            .body(testReport)
            .pathParam("reportedUserId", 22)
            .auth().oauth2(generateValidUserToken())
            .contentType(ContentType.JSON)
            .when().post("/report/{reportedUserId}")
            .then()
                .statusCode(201);
    }

    @Test
    public void testStudentNotFound() {

        String testReport = Json.createObjectBuilder().add("type", "fakeProfile")
                .build().toString();

        given()
            .body(testReport)
            .pathParam("reportedUserId", -22)
            .auth().oauth2(generateValidUserToken())
            .contentType(ContentType.JSON)
            .when().post("/report/{reportedUserId}")
            .then()
                .statusCode(404);
    }

    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io")
                .groups(Set.of("Admin", "User")).claim("id", 22).expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES))
                .sign();
    }
}
