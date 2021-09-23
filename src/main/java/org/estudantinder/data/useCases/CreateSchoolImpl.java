package org.estudantinder.data.useCases;

import org.estudantinder.data.interfaces.SchoolRepository;
import org.estudantinder.domain.dto.CreateSchoolDTO;
import org.estudantinder.domain.models.School;
import org.estudantinder.domain.useCases.CreateSchool;
import org.estudantinder.shared.exception.AppException;

public class CreateSchoolImpl implements CreateSchool {

    private SchoolRepository schoolRepository;

    public CreateSchoolImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School create(CreateSchoolDTO schoolDTO) {
        School school = this.schoolRepository.findByName(schoolDTO.name);
        if(school != null) 
            throw new AppException("School already exists", "Escola já existe", 409);

        return this.schoolRepository.create(schoolDTO);
    }
}
