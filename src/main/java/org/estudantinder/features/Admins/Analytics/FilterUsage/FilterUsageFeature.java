package org.estudantinder.features.Admins.Analytics.FilterUsage;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.entities.Preferences;
import org.estudantinder.repositories.PreferencesRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class FilterUsageFeature {

    @Inject
    PreferencesRepository preferencesRepository;

    @Inject
    UsersRepository usersRepository;

    public FilterUsageDTO createFilterUsageDTO(String name, List<Preferences> allPreferences, Long filterQuantity,
            Long appUsers) {
        FilterUsageDTO newFilterUsage = new FilterUsageDTO();
        newFilterUsage.name = name;
        newFilterUsage.total = filterQuantity;
        newFilterUsage.percent = (double) (filterQuantity / (double) appUsers);

        return newFilterUsage;
    }

    public void addSchoolYearStatistics(List<Preferences> allPreferences, List<FilterUsageDTO> filterUsageDTOs,
            Long appUsers) {
        Long filterQuantity = allPreferences.stream().filter(preferences -> preferences.getSchool_year() != 0).count();

        filterUsageDTOs.add(createFilterUsageDTO("Serie", allPreferences, filterQuantity, appUsers));
    }

    public void addShiftStatistics(List<Preferences> allPreferences, List<FilterUsageDTO> filterUsageDTOs,
            Long appUsers) {
        Long filterQuantity = allPreferences.stream().filter(preferences -> preferences.getShift() != 0).count();

        filterUsageDTOs.add(createFilterUsageDTO("Turno", allPreferences, filterQuantity, appUsers));
    }

    public void addGenderStatistics(List<Preferences> allPreferences, List<FilterUsageDTO> filterUsageDTOs,
            Long appUsers) {
        Long filterQuantity = allPreferences.stream().filter(preferences -> preferences.getGender() != null).count();

        filterUsageDTOs.add(createFilterUsageDTO("Genero", allPreferences, filterQuantity, appUsers));
    }

    public void addCourseStatistics(List<Preferences> allPreferences, List<FilterUsageDTO> filterUsageDTOs,
            Long appUsers) {
        Long filterQuantity = allPreferences.stream().filter(preferences -> preferences.getCourse() != null).count();

        filterUsageDTOs.add(createFilterUsageDTO("Curso", allPreferences, filterQuantity, appUsers));
    }

    public void addSchoolStatistics(List<Preferences> allPreferences, List<FilterUsageDTO> filterUsageDTOs,
            Long appUsers) {
        Long filterQuantity = allPreferences.stream().filter(preferences -> preferences.getSchool() != null).count();

        filterUsageDTOs.add(createFilterUsageDTO("Escola", allPreferences, filterQuantity, appUsers));
    }

    public void addSubjectsStatistics(List<Preferences> allPreferences, List<FilterUsageDTO> filterUsageDTOs,
            Long appUsers) {
        Long filterQuantity = allPreferences.stream().filter(preferences -> preferences.getSubjects() != null).count();

        filterUsageDTOs.add(createFilterUsageDTO("Materia", allPreferences, filterQuantity, appUsers));
    }

    public List<FilterUsageDTO> execute() {

        List<Preferences> preferencesList = preferencesRepository.listAll();

        List<FilterUsageDTO> filterUsageDTOs = new ArrayList<FilterUsageDTO>();
        Long appUsers = usersRepository.count();

        addSchoolStatistics(preferencesList, filterUsageDTOs, appUsers);
        addCourseStatistics(preferencesList, filterUsageDTOs, appUsers);
        addSchoolYearStatistics(preferencesList, filterUsageDTOs, appUsers);
        addShiftStatistics(preferencesList, filterUsageDTOs, appUsers);
        addGenderStatistics(preferencesList, filterUsageDTOs, appUsers);
        addSubjectsStatistics(preferencesList, filterUsageDTOs, appUsers);

        return filterUsageDTOs;
    }
}
