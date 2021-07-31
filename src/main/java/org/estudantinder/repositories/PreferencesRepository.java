package org.estudantinder.repositories;

import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.Subject;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PreferencesRepository implements PanacheRepository<Preferences> {
    public Stream<Preferences> findBySubject(Subject subject){

        //for some reason streamAll() doesn't work
        List<Preferences> listedPreferences = listAll();

        Stream<Preferences> streamedPreferences = listedPreferences.stream();

        return streamedPreferences.filter(preferences -> preferences.getSubjects().contains(subject));
    }
}
