package org.estudantinder.Features.Student;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

import javax.json.Json;


@QuarkusTest
public class AuthenticateStudentTest {

    @Test
    public void testCreateSchoolEndpoint() {

        String testStudent = Json.createObjectBuilder()
        .add("email", "teste1@gmail.com")
        .add("password", "TEST PASSWORD 1")
        .build().toString();
        
        given()
            .body(testStudent)
            .contentType(ContentType.JSON)
            .when().post("/student/login")
            .then()
                .statusCode(200)
                .body(containsString("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9"));
    }
}
