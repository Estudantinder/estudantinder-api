package org.estudantinder.features.Statistics.FilterUsage;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.entities.Preferences;
import org.estudantinder.repositories.PreferencesRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    PreferencesRepository preferencesRepository;

    @Inject
    UsersRepository usersRepository;

    public Long getSchoolYearUsage(List<Preferences> allPreferences) {
        return allPreferences.stream().filter(preferences -> preferences.getSchool_year() != 0).count();
    }

    //get shift usage
    public Long getShiftUsage(List<Preferences> allPreferences) {
        return allPreferences.stream().filter(preferences -> preferences.getShift() != 0).count();
    }

    //get gender usage
    public Long getGenderUsage(List<Preferences> allPreferences) {
        return allPreferences.stream().filter(preferences -> preferences.getGender() != null).count();
    }

    //get course usage
    public Long getCourseUsage(List<Preferences> allPreferences) {
        return allPreferences.stream().filter(preferences -> preferences.getCourse() != null).count();
    }

    //get school usage
    public Long getSchoolUsage(List<Preferences> allPreferences) {
        return allPreferences.stream().filter(preferences -> preferences.getSchool() != null).count();
    }

    //get subjects usage
    public Long getSubjectsUsage(List<Preferences> allPreferences) {
        return allPreferences.stream().filter(preferences -> preferences.getSubjects() != null).count();
    }


    public FilterUsageDTO execute() {

        List<Preferences> preferencesList = preferencesRepository.listAll();

        FilterUsageDTO filterUsageDTO = new FilterUsageDTO();
        
        filterUsageDTO.quantity_of_school_year = getSchoolUsage(preferencesList);
        filterUsageDTO.quantity_of_shift = getShiftUsage(preferencesList);
        filterUsageDTO.quantity_of_gender = getGenderUsage(preferencesList);
        filterUsageDTO.quantity_of_course = getGenderUsage(preferencesList);
        filterUsageDTO.quantity_of_school = getSchoolUsage(preferencesList);
        filterUsageDTO.quantity_of_subjects = getSubjectsUsage(preferencesList);

        return  filterUsageDTO;
    }
}
