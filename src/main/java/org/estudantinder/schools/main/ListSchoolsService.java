package org.estudantinder.schools.main;

import java.util.List;

import javax.inject.Singleton;

import org.estudantinder.schools.data.useCases.ListSchoolsImpl;
import org.estudantinder.schools.domain.models.School;
import org.estudantinder.schools.domain.useCases.ListSchools;
import org.estudantinder.schools.infra.panache.repositories.PanacheSchoolRepository;

@Singleton
public class ListSchoolsService {

    ListSchools listSchools;

    public ListSchoolsService() {
        PanacheSchoolRepository panacheSchoolRepository = new PanacheSchoolRepository();
        this.listSchools = new ListSchoolsImpl(panacheSchoolRepository);
    }

    public List<School> handle() {
        return listSchools.listAll();
    }
}