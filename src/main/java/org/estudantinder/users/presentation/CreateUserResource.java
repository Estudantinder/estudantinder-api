package org.estudantinder.users.presentation;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
import org.estudantinder.users.domain.dtos.CreateUserDTO;
import org.estudantinder.users.domain.models.User;
import org.estudantinder.users.main.CreateUserService;

@Path("users")
@Tag(name = "User", description = "Endpoints related to logged user manipulation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateUserResource {

    @Inject
    CreateUserService createUserService;

    @POST
    @Transactional
    @Operation(summary = "Create a new user")
    @APIResponse(
        responseCode = "201",
        description = "Created",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class)
        )
    )
    @APIResponse(
        responseCode = "400",
        description = "Bad Request",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    @APIResponse(
        responseCode = "409",
        description = "Email already in use",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            example = 
            "{\n" +
                "\"code\":\"409\",\n" +
                "\"message\":\"Email já está em uso\"\n" +
            "}"
        )
    )
    public Response handle(@Valid CreateUserDTO createUserDTO) {
        return Response
            .status(201)
            .entity(createUserService.handle(createUserDTO))
            .build();
    }
}
