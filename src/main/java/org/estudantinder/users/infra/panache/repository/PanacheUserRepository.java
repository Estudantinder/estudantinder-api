package org.estudantinder.users.infra.panache.repository;

import java.util.UUID;

import org.estudantinder.schools.infra.panache.entities.PanacheCourse;
import org.estudantinder.schools.infra.panache.entities.PanacheSchool;
import org.estudantinder.shared.utils.EncryptUtils;
import org.estudantinder.users.data.interfaces.UserRepository;
import org.estudantinder.users.domain.dtos.CreateContactsDTO;
import org.estudantinder.users.domain.dtos.CreatePreferencesDTO;
import org.estudantinder.users.domain.dtos.CreateUserDTO;
import org.estudantinder.users.domain.models.User;
import org.estudantinder.users.infra.panache.entities.PanacheContacts;
import org.estudantinder.users.infra.panache.entities.PanachePreferences;
import org.estudantinder.users.infra.panache.entities.PanacheUser;

public class PanacheUserRepository implements UserRepository {


    private PanacheContacts createPanacheContacts(CreateContactsDTO createContactsDTO) {
        PanacheContacts panacheContacts = new PanacheContacts();

        panacheContacts.id = UUID.randomUUID();
        panacheContacts.whatsapp = createContactsDTO.whatsapp;
        panacheContacts.facebook = createContactsDTO.facebook;
        panacheContacts.twitter = createContactsDTO.twitter;
        panacheContacts.instagram = createContactsDTO.instagram;

        panacheContacts.persist();

        return panacheContacts;
    }

    private PanachePreferences createPanachePreferences(CreatePreferencesDTO createPreferencesDTO) {
        PanachePreferences panachePreferences = new PanachePreferences();

        panachePreferences.id = UUID.randomUUID();
        panachePreferences.shift = createPreferencesDTO.shift;
        panachePreferences.school_year = createPreferencesDTO.school_year;
        panachePreferences.course = PanacheCourse.findById(createPreferencesDTO.course_id);
        panachePreferences.school = PanacheSchool.findById(createPreferencesDTO.school_id);
        
        panachePreferences.persist();

        return panachePreferences;
    }

    private PanacheUser createPanacheUser(CreateUserDTO createUserDTO) {
        
        PanacheUser panacheUser = new PanacheUser();
        
        panacheUser.id = UUID.randomUUID();
        panacheUser.name = createUserDTO.name;
        panacheUser.email = createUserDTO.email;
        panacheUser.password = EncryptUtils.encrypt(createUserDTO.password);
        panacheUser.birth_date = createUserDTO.birth_date;
        panacheUser.bio = createUserDTO.bio;
        panacheUser.gender = createUserDTO.gender;
        panacheUser.school_year = createUserDTO.school_year;
        panacheUser.shift = createUserDTO.shift;
        panacheUser.classroom = createUserDTO.classroom.charAt(0);
        panacheUser.course = PanacheCourse.findById(createUserDTO.course_id);
        panacheUser.contacts = createPanacheContacts(createUserDTO.contacts);
        if(createUserDTO.preferences != null)
            panacheUser.preferences = createPanachePreferences(createUserDTO.preferences);

        panacheUser.persist();

        return panacheUser;
    }

    @Override
    public User create(CreateUserDTO createUserDTO) {
        return createPanacheUser(createUserDTO).toUser();
    }

    @Override
    public User findByEmail(String email) {
        PanacheUser user = PanacheUser.find("email", email).firstResult();

        if(user == null)
            return null;
        
        return user.toUser();
    }

}
