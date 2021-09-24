package org.estudantinder.schools.data.useCases;

import java.util.List;

import org.estudantinder.schools.data.interfaces.SchoolRepository;
import org.estudantinder.schools.domain.models.School;
import org.estudantinder.schools.domain.useCases.ListSchools;

public class ListSchoolsImpl implements ListSchools {

    private SchoolRepository schoolRepository;

    public ListSchoolsImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> listAll() {
        return this.schoolRepository.listAll();
    }

}
