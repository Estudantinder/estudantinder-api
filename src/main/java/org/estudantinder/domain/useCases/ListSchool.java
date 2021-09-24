package org.estudantinder.domain.useCases;

import java.util.UUID;

import org.estudantinder.domain.models.School;

public interface ListSchool {
    School findById(UUID schoolId);
}
