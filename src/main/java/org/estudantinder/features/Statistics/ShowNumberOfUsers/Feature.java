package org.estudantinder.features.Statistics.ShowNumberOfUsers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    public NumberOfUsersDTO execute() {
        NumberOfUsersDTO result = new NumberOfUsersDTO();

        result.numberOfUsers = usersRepository.count();

        return  result;
    }
}
