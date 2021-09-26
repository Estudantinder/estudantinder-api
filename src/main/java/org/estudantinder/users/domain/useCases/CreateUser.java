package org.estudantinder.users.domain.useCases;

import org.estudantinder.users.domain.dtos.CreateUserDTO;
import org.estudantinder.users.domain.models.User;

public interface CreateUser {
    User create(CreateUserDTO createUserDTO);
}
