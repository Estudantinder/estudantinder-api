package org.estudantinder.main;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.estudantinder.data.useCases.CreateSchoolImpl;
import org.estudantinder.domain.dto.CreateSchoolDTO;
import org.estudantinder.domain.models.School;
import org.estudantinder.domain.useCases.CreateSchool;
import org.estudantinder.infra.panache.repositories.PanacheSchoolRepository;

@Singleton
public class CreateSchoolService {
    
    CreateSchool createSchool;

    public CreateSchoolService() {
        PanacheSchoolRepository panacheSchoolRepository = new PanacheSchoolRepository();
        this.createSchool = new CreateSchoolImpl(panacheSchoolRepository);
    }

    @Transactional
    public School handle(CreateSchoolDTO createSchoolDTO) {
        return this.createSchool.create(createSchoolDTO);
    }
}
