package org.estudantinder.Features.School;

import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

@QuarkusTest
public class UpdateSchoolTest {
    
    @Test
    public void testUpdateSchoolEndpoint() {
        
        Course newCourse = new Course();
        Course newCourse1 = new Course();
        newCourse.setName("New Course");
        newCourse1.setName("New Course1");

        School updatedSchool = new School();
        updatedSchool.setCourses(Arrays.asList(newCourse, newCourse1));

        given()
            .body(updatedSchool)
            .pathParam("id", -3)
            .contentType(ContentType.JSON)
            .when().put("/school/{id}")
            .then()
                .statusCode(200);
    }
}
