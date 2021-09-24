package org.estudantinder.schools.main;

import java.util.UUID;

import javax.inject.Singleton;

import org.estudantinder.schools.data.useCases.ListSchoolImpl;
import org.estudantinder.schools.domain.models.School;
import org.estudantinder.schools.domain.useCases.ListSchool;
import org.estudantinder.schools.infra.panache.repositories.PanacheSchoolRepository;

@Singleton
public class ListSchoolService {

    ListSchool listSchool;

    public ListSchoolService() {
        PanacheSchoolRepository panacheSchoolRepository = new PanacheSchoolRepository();
        this.listSchool = new ListSchoolImpl(panacheSchoolRepository);
    }

    public School handle(UUID schoolId) {
        return listSchool.findById(schoolId);
    }
}
