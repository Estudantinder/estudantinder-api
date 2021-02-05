package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Preferences;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PreferencesRepository implements PanacheRepository<Preferences> {
    
}
