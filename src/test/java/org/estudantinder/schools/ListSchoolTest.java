package org.estudantinder.schools;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class ListSchoolTest {

    @Test
    public void testListSchoolCreated() {
        Map<String, String> testCourse = new HashMap<>();
        testCourse.put("id", "37a77482-61ea-45e5-935e-2b487a1c0299");
        testCourse.put("name", "course 1");

        given()
            .pathParam("schoolId", "b68fa2c4-0463-4783-8ac0-c97edd0d34f4")
            .when().get("/schools/{schoolId}")
            .then()
                .statusCode(200)
                .body("id", instanceOf(String.class))
                .body("name", equalTo("School 1"))
                .body("address", equalTo("School 1 Adress"))
                .body("courses", hasItem(testCourse));
    }

    @Test
    public void testListSchoolConflict() {
        given()
            .pathParam("schoolId", "b68fa2c4-0463-4783-8ac0-c97edd0d34f0")
            .when().get("/schools/{schoolId}")
            .then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", equalTo("Escola não encontrada"));
    }
}