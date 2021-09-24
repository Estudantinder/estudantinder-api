package org.estudantinder.schools.data.interfaces;

import java.util.List;
import java.util.UUID;

import org.estudantinder.schools.domain.models.School;

public interface SchoolRepository {
    School findById(UUID schoolId);
    List<School> listAll();
}
