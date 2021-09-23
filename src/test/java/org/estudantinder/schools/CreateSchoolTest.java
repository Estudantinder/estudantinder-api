package org.estudantinder.schools;

import javax.inject.Inject;
import javax.json.Json;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

import java.util.ArrayList;

@QuarkusTest
public class CreateSchoolTest {

    @Inject
    ObjectMapper objectMapper;
    
    @Test
    public void testCreateSchoolCreated() {

        String testSchool = Json.createObjectBuilder()
            .add("name", "test school")
            .add("address", "test address")
            .add("courses", Json.createArrayBuilder().add(
                Json.createObjectBuilder().add("name", "test course")
            ))
            .build().toString();

        given()
            .body(testSchool)
            .contentType(ContentType.JSON)
            .when().post("/schools")
            .then()
                .statusCode(201)
                .body("id", isA(String.class))
                .body("name", is("test school"))
                .body("address", is("test address"))
                .body("courses", CoreMatchers.instanceOf(ArrayList.class));
    }

    @Test
    public void testCreateSchoolConflict() {

        String testSchool = Json.createObjectBuilder()
            .add("name", "test school 1")
            .add("address", "test address 1")
            .add("courses", Json.createArrayBuilder().add(
                Json.createObjectBuilder().add("name", "test course")
            ))
            .build().toString();


        given()
            .body(testSchool)
            .contentType(ContentType.JSON)
            .when().post("/schools");

        given()
            .body(testSchool)
            .contentType(ContentType.JSON)
            .when().post("/schools")
            .then()
                .statusCode(409)
                .body("code", is(409))
                .body("message", is("School already exists"))
                .body("message_ptBR", is("Escola já existe"));
    }
}