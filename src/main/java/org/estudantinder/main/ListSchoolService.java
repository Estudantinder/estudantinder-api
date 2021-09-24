package org.estudantinder.main;

import java.util.UUID;

import javax.inject.Singleton;

import org.estudantinder.data.useCases.ListSchoolImpl;
import org.estudantinder.domain.models.School;
import org.estudantinder.domain.useCases.ListSchool;
import org.estudantinder.infra.panache.repositories.PanacheSchoolRepository;

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
