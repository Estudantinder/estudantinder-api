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
    public void testCreateStudentEndpoint() {

        String testStudent = Json.createObjectBuilder()
        .add("name", "TEST STUDENT")
        .add("email", "test@email.com")
        .add("password", "TEST PASSWORD 1")
        .add("school_year", 3)
        .add("birth_date", "2000-02-01")    
        .add("classroom", "F")
        .add("bio", "TEST BIOGRAPHY")
        .add("gender", "1")
        .add("shift", 1)
        .add("photos", Json.createArrayBuilder()
            .add("http://testPhoto1.com")
            .add("http://testPhoto2.com")
            .add("http://testPhoto3.com")
            .add("http://testPhoto4.com")
            .add("http://testPhoto5.com")
            .add("http://testPhoto6.com"))
        .add("subjects_id", Json.createArrayBuilder()
            .add(10)
            .add(11)
            .add(12))
        .add("course_id", 5)
        .add("contacts", Json.createObjectBuilder()
            .add("whatsapp", 551112345)
            .add("instagram", "@testInstagram")
            .add("twitter", "@testTwitter")
            .add("facebook", "test.facebook"))
        .add("preferences", Json.createObjectBuilder()
            .add("school_year", 3)
            .add("shift", 1)
            .add("course_id", 5))
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
