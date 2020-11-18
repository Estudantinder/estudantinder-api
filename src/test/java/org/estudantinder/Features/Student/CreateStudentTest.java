package org.estudantinder.Features.Student;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

import javax.json.Json;


@QuarkusTest
public class CreateStudentTest {

    @Test
    public void testCreateSchoolEndpoint() {

        String testStudent = Json.createObjectBuilder()
        .add("name", "TEST STUDENT")
        .add("email", "TEST EMAIL")
        .add("password", "TEST PASSWORD")
        .add("schoolYear", 3)
        .add("birthday", "2000-02-01")
        .add("biography", "TEST BIOGRAPHY")
        .add("photos", Json.createArrayBuilder()
            .add("http://testPhoto1.com")
            .add("http://testPhoto2.com")
            .add("http://testPhoto3.com")
            .add("http://testPhoto4.com")
            .add("http://testPhoto5.com")
            .add("http://testPhoto6.com"))
        .add("favoriteSubjects", Json.createArrayBuilder()
            .add("SUBJECT 1")
            .add("SUBJECT 2")
            .add("subject 3"))
        .add("courseId", 6)
        .add("contacts", Json.createObjectBuilder()
            .add("whatsapp", 551112345)
            .add("instagram", "@testInstagram")
            .add("twitter", "@testTwitter")
            .add("facebook", "test.facebook"))
        .add("preferences", Json.createObjectBuilder()
            .add("schoolYear", 3)
            .add("schoolShift", "tarde")
            .add("courseId", 8))
            .build().toString();
        
        given()
            .body(testStudent)
            .contentType(ContentType.JSON)
            .when().post("/student")
            .then()
                .statusCode(201)
                .body("name", containsString("TEST STUDENT"));;
    }
}
