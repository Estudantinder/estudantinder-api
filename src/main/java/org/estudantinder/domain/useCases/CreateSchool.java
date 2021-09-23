package org.estudantinder.domain.useCases;

import org.estudantinder.domain.dto.CreateSchoolDTO;
import org.estudantinder.domain.models.School;

public interface CreateSchool {
    School create(CreateSchoolDTO school);
}
