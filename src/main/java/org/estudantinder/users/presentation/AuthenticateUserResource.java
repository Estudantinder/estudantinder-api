package org.estudantinder.users.presentation;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.shared.exception.ErrorResponse;
import org.estudantinder.users.domain.dtos.authentication.AuthenticationRequestDTO;
import org.estudantinder.users.domain.dtos.authentication.AuthenticationResponseDTO;
import org.estudantinder.users.main.AuthenticateUserService;

@Path("users")
@Tag(name = "User", description = "Endpoints related to logged user manipulation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticateUserResource {
    
    @Inject
    AuthenticateUserService authenticateUserService;

    @POST
    @Operation(summary = "Authenticate a user")
    @Path("/authenticate")
    @APIResponse(
        responseCode = "200",
        description = "Authenticated",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AuthenticationResponseDTO.class)
        )
    )
    @APIResponse(
        responseCode = "404",
        description = "User not found",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            example =  "{\n" +
                "\"code\":\"404\",\n" +
                "\"message\":\"Usuário não encontrado\"\n" +
            "}"
        )
    )
    @APIResponse(
        responseCode = "401",
        description = "Incorrect Password",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            example =  "{\n" +
                "\"code\":\"401\",\n" +
                "\"message\":\"Senha Incorreta\"\n" +
            "}"
        )
    )
    public Response handle(@Valid AuthenticationRequestDTO authenticationRequestDTO) {
        return Response
            .status(200)
            .entity(authenticateUserService.handle(authenticationRequestDTO))
            .build();
    }
}
