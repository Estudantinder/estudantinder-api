package org.estudantinder.schools.infra.panache.repositories;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.estudantinder.schools.data.interfaces.SchoolRepository;
import org.estudantinder.schools.domain.models.School;
import org.estudantinder.schools.infra.panache.entities.PanacheSchool;

public class PanacheSchoolRepository implements SchoolRepository {

    List<School> mapPanacheSchoolsToSchool(List<PanacheSchool> allSchools) {
        return allSchools.stream().map(PanacheSchool::toSchool)
            .collect(Collectors.toList());
    }

    @Override
    public School findById(UUID schoolId) {
        PanacheSchool school = PanacheSchool.findById(schoolId);

        if(school == null) return null;

        return school.toSchool();
    }

    @Override
    public List<School> listAll() {
        List<PanacheSchool> allSchools = PanacheSchool.listAll();

        return mapPanacheSchoolsToSchool(allSchools);
    }
}
