package org.estudantinder.Features.Users;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


@QuarkusTest
public class ImageUploadTest {

    @Test
    public void testImageUploadEndpoint() throws FileNotFoundException {
        File photo = new File("src/test/resources/placeholderImage1.jpeg");
        given()
            .multiPart("photo0", photo)
            .accept(ContentType.JSON)
            .auth().oauth2(generateValidUserToken())
            .when().post("/users/imageUpload")
            .then()
                .statusCode(200);
    }

    @Test
    public void testNotFoundImageUploadEndpoint() throws FileNotFoundException {
        File photo = new File("src/test/resources/placeholderImage1.jpeg");
        given()
            .multiPart("photo0", photo)
            .accept(ContentType.JSON)
            .auth().oauth2(generateNonExistentUserToken())
            .when().post("/users/imageUpload")
            .then()
                .statusCode(404);
    }

    static String generateValidUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", 22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }

    static String generateNonExistentUserToken() {
        return Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("User")
            .claim("id", -22)
            .expiresAt(Instant.now().plus(2, ChronoUnit.MINUTES ))
            .sign(); 
    }
}