package org.estudantinder.schools;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ListSchoolsTest {

    @Inject
    ObjectMapper objectMapper;

    
    @Test
    public void testListSchoolCreated() {
        
        given()
            .when().get("/schools")
            .then()
                .statusCode(200)
                .body(CoreMatchers.notNullValue());
    }
}