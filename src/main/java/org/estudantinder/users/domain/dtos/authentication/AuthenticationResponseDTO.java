package org.estudantinder.users.domain.dtos.authentication;

import java.time.Instant;

public class AuthenticationResponseDTO {
    public String token;
    public Instant expirationDate;
}
