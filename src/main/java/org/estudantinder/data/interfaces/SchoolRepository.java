package org.estudantinder.data.interfaces;

import java.util.List;
import java.util.UUID;

import org.estudantinder.domain.dto.CreateSchoolDTO;
import org.estudantinder.domain.models.School;

public interface SchoolRepository {
    School findById(UUID schoolId);
    School findByName(String name);
    School create(CreateSchoolDTO school);
    List<School> listAll();
}
