package org.estudantinder.features.Schools.UpdateSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.estudantinder.features.Schools.UpdateSchool.DTO.SchoolDTO;
import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.SchoolsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SchoolsRepository schoolsRepository;
    
    @Inject
    CoursesRepository coursesRepository;


    public void execute(Long id, SchoolDTO data) throws Exception {
        School updatedSchool = schoolsRepository.findById(id);

        if(updatedSchool == null) {
            throw new NotFoundException("Não foi possivel achar a escola");
        }



        if(data.name != null ) {
            updatedSchool.setName(data.name);
        }
        if(data.address != null ) {
            updatedSchool.setAddress(data.address);
        }
        if(data.courses != null) {
            coursesRepository.delete("school", updatedSchool);

            data.courses.forEach(course -> {
                Course newCourse = new Course();
                newCourse.setName(course.name);
                newCourse.setSchool(updatedSchool);
                coursesRepository.persist(newCourse);
            });
        }


        schoolsRepository.persist(updatedSchool);
    }

}