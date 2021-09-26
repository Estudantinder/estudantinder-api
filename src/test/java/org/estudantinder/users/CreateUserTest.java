package org.estudantinder.users;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.containsString;

import javax.json.Json;
import javax.json.JsonObject;

@QuarkusTest
public class CreateUserTest {

    @Test
    public void testCreateUser() {

        JsonObject testUser = Json.createObjectBuilder()
        .add("name", "test user")
        .add("password", "SecretPassword123")
        .add("email", "test@email.com")
        .add("birth_date", "2006-06-06")
        .add("bio", "kpop very good")
        .add("gender", "0")
        .add("school_year", 1)
        .add("shift", 0)
        .add("classroom", "F")
        .add("course_id", "37a77482-61ea-45e5-935e-2b487a1c0299")
        .add("contacts", Json.createObjectBuilder()
            .add("whatsapp", 5511999999999L)
            .add("twitter", "@elonmusk")
            .add("facebook", "elon.musk")
            .add("instagram", "elonmusk"))
        .add("preferences", Json.createObjectBuilder()
            .add("course_id", "37a77482-61ea-45e5-935e-2b487a1c0299")
            .add("school_id", "b68fa2c4-0463-4783-8ac0-c97edd0d34f4")
            .add("school_year", 2)
            .add("shift", 0)).build();

        given()
        .body(testUser.toString())
        .contentType(ContentType.JSON)
        .when().post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo(testUser.getString("name")))
            .body("password", nullValue())
            .body("email", equalTo(testUser.getString("email")))
            .body("birth_date", containsString(testUser.getString("birth_date")))
            .body("bio", equalTo(testUser.getString("bio")))
            .body("gender", equalTo(testUser.getString("gender")))
            .body("school_year", equalTo(testUser.getInt("school_year")))
            .body("shift", equalTo(testUser.getInt("shift")))
            .body("classroom", equalTo(testUser.getString("classroom")))
            .body("course.id", equalTo(testUser.getString("course_id")))
            .body("contacts.whatsapp", equalTo(testUser.getJsonObject("contacts").getJsonNumber("whatsapp").longValue()))
            .body("contacts.twitter", equalTo(testUser.getJsonObject("contacts").getString("twitter")))
            .body("contacts.facebook", equalTo(testUser.getJsonObject("contacts").getString("facebook")))
            .body("contacts.instagram", equalTo(testUser.getJsonObject("contacts").getString("instagram")))
            .body("preferences.course.id", equalTo(testUser.getJsonObject("preferences").getString("course_id")))
            .body("preferences.school.id", equalTo(testUser.getJsonObject("preferences").getString("school_id")))
            .body("preferences.school_year", equalTo(testUser.getJsonObject("preferences").getInt("school_year")))
            .body("preferences.shift", equalTo(testUser.getJsonObject("preferences").getInt("shift")))
            ;
    }

    @Test
    public void testCreateUserConflict() {

        JsonObject testUser = Json.createObjectBuilder()
        .add("name", "test user")
        .add("password", "SecretPassword123")
        .add("email", "test1@email.com")
        .add("birth_date", "2006-06-06")
        .add("bio", "kpop very good")
        .add("gender", "0")
        .add("school_year", 1)
        .add("shift", 0)
        .add("classroom", "F")
        .add("course_id", "37a77482-61ea-45e5-935e-2b487a1c0299")
        .add("contacts", Json.createObjectBuilder()
            .add("instagram", "elonmusk"))
        .build();

        given()
        .body(testUser.toString())
        .contentType(ContentType.JSON)
        .when().post("/users");

        given()
        .body(testUser.toString())
        .contentType(ContentType.JSON)
        .when().post("/users")
        .then()
            .statusCode(409)
            .body("code", equalTo(409))
            .body("message", equalTo("Email já está em uso"));
    }

}