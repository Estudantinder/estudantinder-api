package org.estudantinder.schools.domain.useCases;

import java.util.UUID;

import org.estudantinder.schools.domain.models.School;

public interface ListSchool {
    School findById(UUID schoolId);
}
