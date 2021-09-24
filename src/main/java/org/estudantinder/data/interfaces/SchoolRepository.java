package org.estudantinder.data.interfaces;

import java.util.List;
import java.util.UUID;

import org.estudantinder.domain.models.School;

public interface SchoolRepository {
    School findById(UUID schoolId);
    List<School> listAll();
}
