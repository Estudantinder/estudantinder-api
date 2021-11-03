package org.estudantinder.features.Admins.Analytics.NumberOfUsers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class NumberOfUsersFeature {

    @Inject
    UsersRepository usersRepository;

    public Long execute() {
        return usersRepository.count();
    }
}
