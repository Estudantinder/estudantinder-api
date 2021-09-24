package org.estudantinder.data.interfaces;

import java.util.List;
import java.util.UUID;

import org.estudantinder.domain.dto.CreateSchoolDTO;
import org.estudantinder.domain.models.School;

public interface SchoolRepository {
    School findByName(String name);
    School create(CreateSchoolDTO school);
    List<School> listAll();
}
