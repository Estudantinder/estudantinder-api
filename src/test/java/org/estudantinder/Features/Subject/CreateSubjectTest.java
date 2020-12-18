package org.estudantinder.Features.Subject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.containsString;

import javax.json.Json;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateSubjectTest {
    
    @Test
    public void testCreateSubjectEndpoint() {

        String testSubject = Json.createObjectBuilder()
            .add("name", "TEST SUBJECT")
            .build().toString();

        given()
            .body(testSubject)
            .contentType(ContentType.JSON)
            .when().post("/subject")
            .then()
                .statusCode(201)
                .body("name", containsString("TEST SUBJECT"));
    }
}
