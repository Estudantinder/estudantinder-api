package org.estudantinder.data.useCases;

import java.util.List;

import org.estudantinder.data.interfaces.SchoolRepository;
import org.estudantinder.domain.models.School;
import org.estudantinder.domain.useCases.ListSchools;

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
