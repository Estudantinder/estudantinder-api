package org.estudantinder.users.data.interfaces;

import org.estudantinder.users.domain.dtos.CreateUserDTO;
import org.estudantinder.users.domain.models.User;

public interface UserRepository {
    User create(CreateUserDTO createUserDTO);
    User findByEmail(String email);
}
