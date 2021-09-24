package org.estudantinder.main;

import java.util.List;

import javax.inject.Singleton;

import org.estudantinder.data.useCases.ListSchoolsImpl;
import org.estudantinder.domain.models.School;
import org.estudantinder.domain.useCases.ListSchools;
import org.estudantinder.infra.panache.repositories.PanacheSchoolRepository;

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
