package org.estudantinder.features.Users.DeleteUser;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
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

@Path("users")
@Tag(name = "Users")
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {


    @Inject
    Controller banReportedUserController;

    @Inject
    JsonWebToken jwt;

    @DELETE
    @Path("me")
    @Transactional
    @RolesAllowed("User")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "200", description = "User successfully banned")
    @APIResponse(responseCode = "404", description = "User not found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Delete logged user")
    public Response banReportedUser() throws Exception {
        return banReportedUserController.handle(jwt);
    }
}