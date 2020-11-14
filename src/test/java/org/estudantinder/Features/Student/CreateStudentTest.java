package org.estudantinder.Features.Student;

import org.estudantinder.features.Student.CreateStudent.DTO.ContactsDTO;
import org.estudantinder.features.Student.CreateStudent.DTO.PreferencesDTO;
import org.estudantinder.features.Student.CreateStudent.DTO.StudentDTO;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.containsString;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateStudentTest {
    
    @Test
    public void testCreateSchoolEndpoint() {
        
        ContactsDTO testContacts = new ContactsDTO();
        
        testContacts.whatsapp = (long) 123456789;
        testContacts.facebook = "test.facebook";
        testContacts.twitter = "@testTwitter";
        testContacts.instagram = "@testInstagram";

        PreferencesDTO testPreferences = new PreferencesDTO();

        testPreferences.courseId = (long) 7;
        testPreferences.gender = "HELICOPTERO DE ATAQUE AH-64D Apache Longbow";
        testPreferences.schoolShift = "TARDE";
        testPreferences.schoolYear = 3;
        
        String[] testPhotos = {
            "https://testPhoto1.com",
            "https://testPhoto2.com",
            "https://testPhoto3.com",
            "https://testPhoto4.com",
            "https://testPhoto5.com",
            "https://testPhoto6.com"
        };

        String[] testFavoriteSubjects = {
            "TEST SUBJECT 1",
            "TEST SUBJECT 2",
            "TEST SUBJECT 3"
        };

        StudentDTO testStudent = new StudentDTO();

        // Doesn't create the birthday, because the
        // LocalDate.of is not correctly formatted, for some reason
        testStudent.name = "TEST NAME";
        testStudent.email = "TEST EMAIL";
        testStudent.password = "TEST PASSWORD";
        testStudent.schoolYear = 3;
        testStudent.biography = "TEST BIOGRAPHY";
        testStudent.photos = testPhotos;
        testStudent.favoriteSubjects = testFavoriteSubjects;
        testStudent.courseId = (long) 6;
        testStudent.contacts = testContacts;
        testStudent.preferences = testPreferences;

        given()
            .body(testStudent)
            .contentType(ContentType.JSON)
            .when().post("/student")
            .then()
                .statusCode(201)
                .body("name", containsString(testStudent.name));
    }
}
