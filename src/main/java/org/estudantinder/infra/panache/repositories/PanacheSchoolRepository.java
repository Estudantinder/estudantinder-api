package org.estudantinder.infra.panache.repositories;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.estudantinder.data.interfaces.SchoolRepository;
import org.estudantinder.domain.models.School;
import org.estudantinder.infra.panache.entities.PanacheSchool;

public class PanacheSchoolRepository implements SchoolRepository {

    @Override
    public School findById(UUID schoolId) {
        PanacheSchool school = PanacheSchool.findById(schoolId);

        if(school == null) return null;

        return school.toSchool();
    }

    @Override
    public List<School> listAll() {
        List<PanacheSchool> allSchools = PanacheSchool.listAll();

        return allSchools.stream().map(PanacheSchool::toSchool)
            .collect(Collectors.toList());
    }
}
