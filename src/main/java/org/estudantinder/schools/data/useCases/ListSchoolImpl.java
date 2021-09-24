package org.estudantinder.schools.data.useCases;

import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.estudantinder.schools.data.interfaces.SchoolRepository;
import org.estudantinder.schools.domain.models.School;
import org.estudantinder.schools.domain.useCases.ListSchool;

public class ListSchoolImpl implements ListSchool {

    private SchoolRepository schoolRepository;

    public ListSchoolImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School findById(UUID schoolId) {
        School school = this.schoolRepository.findById(schoolId);

        if(school == null)
            throw new WebApplicationException("Escola não encontrada", 404);

        return school;
    }

}
