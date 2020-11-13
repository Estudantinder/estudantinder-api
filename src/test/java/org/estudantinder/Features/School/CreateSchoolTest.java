package org.estudantinder.Features.School;

import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.containsString;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateSchoolTest {
    
    @Test
    public void testCreateSchoolEndpoint() {

        Course testCourse1 = new Course();
        testCourse1.setName("TEST COURSE 1");
        
        Course testCourse2 = new Course();
        testCourse2.setName("TEST COURSE 2");

        School testSchool = new School();

        testSchool.setName("TEST SCHOOL");
        testSchool.setAddress("TEST ADDRESS");
        testSchool.setCourses(Arrays.asList(
            testCourse1, 
            testCourse2)
        );

        given()
            .body(testSchool)
            .contentType(ContentType.JSON)
            .when().post("/school")
            .then()
                .statusCode(201)
                .body("name", containsString(testSchool.getName()));
    }
}
