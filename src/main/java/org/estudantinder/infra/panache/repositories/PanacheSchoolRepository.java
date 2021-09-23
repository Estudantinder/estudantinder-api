package org.estudantinder.infra.panache.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.estudantinder.data.interfaces.SchoolRepository;
import org.estudantinder.domain.dto.CreateCourseDTO;
import org.estudantinder.domain.dto.CreateSchoolDTO;
import org.estudantinder.domain.models.School;
import org.estudantinder.infra.panache.entities.PanacheCourse;
import org.estudantinder.infra.panache.entities.PanacheSchool;

public class PanacheSchoolRepository implements SchoolRepository {

    private PanacheCourse createCourse(CreateCourseDTO createCourseDTO, PanacheSchool school) {
        PanacheCourse panacheCourse = new PanacheCourse();
        panacheCourse.id = UUID.randomUUID();
        panacheCourse.name = createCourseDTO.name;
        panacheCourse.school = school;
        panacheCourse.persist();

        return panacheCourse;
    }

    private List<PanacheCourse> createSchoolCourses(List<CreateCourseDTO> createCourseDTOs, PanacheSchool school) {
        List<PanacheCourse> panacheCourses = new ArrayList<>();

        createCourseDTOs.stream().forEach(createCourseDTO -> {
            panacheCourses.add(
                    createCourse(createCourseDTO, school));
        });

        return panacheCourses;
    }

    @Override
    public School findByName(String name) {
        PanacheSchool panacheSchool = PanacheSchool.find("name", name).firstResult();
        if (panacheSchool != null)
            return panacheSchool.toSchool();

        return null;
    }

    @Override
    public School create(CreateSchoolDTO school) {
        PanacheSchool panacheSchool = new PanacheSchool();
        panacheSchool.id = UUID.randomUUID();
        panacheSchool.name = school.name;
        panacheSchool.address = school.address;
        panacheSchool.courses = createSchoolCourses(school.courses, panacheSchool);

        panacheSchool.persist();

        return panacheSchool.toSchool();
    }

}
