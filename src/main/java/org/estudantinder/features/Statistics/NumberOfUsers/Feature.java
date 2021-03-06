package org.estudantinder.features.Statistics.NumberOfUsers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    public NumberOfUsersDTO execute() {
        NumberOfUsersDTO result = new NumberOfUsersDTO();

        result.number_of_users = usersRepository.count();

        return  result;
    }
}
