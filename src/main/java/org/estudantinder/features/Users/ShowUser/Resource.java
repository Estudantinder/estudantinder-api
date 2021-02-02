package org.estudantinder.features.Users.ShowUser;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("users")
@Tag(name = "Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    JsonWebToken jwt;

    @Inject
    Controller showUserController;

    @GET
    @RolesAllowed("User")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "404", description = "Student id Not Found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Show given JWT user")
    public Response showUser(@Context SecurityContext ctx) throws Exception {
        return showUserController.handle(jwt);
    }
}