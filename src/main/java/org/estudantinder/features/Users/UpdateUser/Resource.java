package org.estudantinder.features.Users.UpdateUser;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.features.Users.UpdateUser.DTO.UserDTO;

@Path("users")
@Tag(name = "Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    JsonWebToken jwt;

    @Inject
    Controller createUserController;

    @PUT
    @Path("me")
    @Transactional
    @RolesAllowed("User")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "200", description = "Updated")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "409", description = "Email Already In Use")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Updated given JWT user")
    public Response createUser(@Valid UserDTO data) throws Exception {
        return createUserController.handle(jwt, data);
    }
}