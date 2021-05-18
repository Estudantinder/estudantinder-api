package org.estudantinder.Features.Users;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;

import javax.json.Json;


@QuarkusTest
public class CreateUserTest {

    @Test
    public void testCreateUserEndpoint() {

        String testUser = Json.createObjectBuilder()
        .add("name", "TestUser1")
        .add("email", "test@email.com")
        .add("password", "TestPassword1")
        .add("school_year", 3)
        .add("birth_date", "2000-02-01")    
        .add("classroom", "F")
        .add("bio", "TEST BIOGRAPHY")
        .add("gender", "1")
        .add("shift", 1)
        .add("subjects_ids", Json.createArrayBuilder()
            .add(10)
            .add(11)
            .add(12))
        .add("course_id", 5)
        .add("contacts", Json.createObjectBuilder()
            .add("whatsapp", 551112345)
            .add("instagram", "@testInstagram")
            .add("twitter", "@testTwitter")
            .add("facebook", "test.facebook"))
            .build().toString();
        
        given()
            .body(testUser)
            .contentType(ContentType.JSON)
            .when().post("/users")
            .then()
                .statusCode(201);
    }

    @Test
    public void testConflictCreateUserEndpoint() {

        String testUser = Json.createObjectBuilder()
        .add("name", "TestUser1")
        .add("email", "test4@email.com")
        .add("password", "TestPassword1")
        .add("school_year", 3)
        .add("birth_date", "2000-02-01")
        .add("classroom", "F")
        .add("bio", "TEST BIOGRAPHY")
        .add("gender", "1")
        .add("shift", 1)
        .add("subjects_ids", Json.createArrayBuilder()
            .add(10)
            .add(11)
            .add(12))
        .add("course_id", 5)
        .add("contacts", Json.createObjectBuilder()
            .add("whatsapp", 551112345)
            .add("instagram", "@testInstagram")
            .add("twitter", "@testTwitter")
            .add("facebook", "test.facebook"))
            .build().toString();
        
        given()
            .body(testUser)
            .contentType(ContentType.JSON)
            .when().post("/users")
            .then()
                .statusCode(409);
    }

    @Test
    public void testBadRequestConflictCreateUserEndpoint() {
        //password without number
        String testUser = Json.createObjectBuilder()
        .add("name", "TestUser1")
        .add("email", "test0@email.com")
        .add("password", "TestPassword")
        .add("school_year", 3)
        .add("birth_date", "2000-02-01")    
        .add("classroom", "F")
        .add("bio", "TEST BIOGRAPHY")
        .add("gender", "1")
        .add("shift", 1)
        .add("subjects_ids", Json.createArrayBuilder()
            .add(10)
            .add(11)
            .add(12))
        .add("course_id", 5)
        .add("contacts", Json.createObjectBuilder()
            .add("whatsapp", 551112345)
            .add("instagram", "@testInstagram")
            .add("twitter", "@testTwitter")
            .add("facebook", "test.facebook"))
            .build().toString();
        
        given()
            .body(testUser)
            .contentType(ContentType.JSON)
            .when().post("/users")
            .then()
                .statusCode(400);
    }
}
