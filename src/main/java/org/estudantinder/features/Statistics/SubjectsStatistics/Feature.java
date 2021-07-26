package org.estudantinder.features.Statistics.SubjectsStatistics;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.entities.Subject;
import org.estudantinder.repositories.PreferencesRepository;
import org.estudantinder.repositories.SubjectsRepository;
import org.estudantinder.repositories.UsersRepository;


@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    PreferencesRepository preferencesRepository;

    @Inject
    SubjectsRepository subjectsRepository;

    public List<SubjectStatistics> addToSubjectStatistics(List<SubjectStatistics> listOfSubjectsStatistics, Subject subject) {
        SubjectStatistics subjectStatistics = new SubjectStatistics(); 
        subjectStatistics.subject_name = subject.getName();
        subjectStatistics.users_preffered= usersRepository.findBySubject(subject).count();
        subjectStatistics.users_searching= preferencesRepository.findBySubject(subject).count();

        listOfSubjectsStatistics.add(subjectStatistics);

        return listOfSubjectsStatistics;
    }


    public List<SubjectStatistics> execute() {

        List<SubjectStatistics> result = new ArrayList<SubjectStatistics>();

        subjectsRepository.listAll().forEach(subject -> addToSubjectStatistics(result, subject));

        return result;
    }
}
