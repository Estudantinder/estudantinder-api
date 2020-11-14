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
        
        Course updatedTestCourse1 = new Course();
        updatedTestCourse1.setName("UPDATED TEST COURSE 1");
        
        Course updatedTestCourse2 = new Course();
        updatedTestCourse2.setName("UPDATED TEST COURSE 2");

        School updatedTestSchool = new School();

        updatedTestSchool.setName("UPDATED TEST SCHOOL");
        updatedTestSchool.setAddress("UPDATED TEST ADDRESS");
        updatedTestSchool.setCourses(Arrays.asList(
            updatedTestCourse1, 
            updatedTestCourse2)
        );

        given()
            .body(updatedTestSchool)
            .pathParam("id", 3)
            .contentType(ContentType.JSON)
            .when().put("/school/{id}")
            .then()
                .statusCode(200);
    }
}
