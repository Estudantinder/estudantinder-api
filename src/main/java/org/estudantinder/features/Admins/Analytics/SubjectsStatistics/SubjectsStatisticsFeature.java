package org.estudantinder.features.Admins.Analytics.SubjectsStatistics;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.entities.Subject;
import org.estudantinder.repositories.PreferencesRepository;
import org.estudantinder.repositories.SubjectsRepository;
import org.estudantinder.repositories.UsersRepository;


@ApplicationScoped
public class SubjectsStatisticsFeature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    PreferencesRepository preferencesRepository;

    @Inject
    SubjectsRepository subjectsRepository;

    public List<SubjectStatisticsDTO> addToSubjectStatistics(List<SubjectStatisticsDTO> listOfSubjectsStatistics, Subject subject) {
        SubjectStatisticsDTO subjectStatistics = new SubjectStatisticsDTO(); 
        subjectStatistics.subject_name = subject.getName();
        subjectStatistics.users_preffered= usersRepository.findBySubject(subject).count();
        subjectStatistics.users_searching= preferencesRepository.findBySubject(subject).count();

        listOfSubjectsStatistics.add(subjectStatistics);

        return listOfSubjectsStatistics;
    }


    public List<SubjectStatisticsDTO> execute() {

        List<SubjectStatisticsDTO> result = new ArrayList<SubjectStatisticsDTO>();

        subjectsRepository.listAll().forEach(subject -> addToSubjectStatistics(result, subject));

        return result;
    }
}
