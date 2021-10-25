package org.estudantinder.features.Admins.AdminJwtValidation;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
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

@Path("admins")
@Tag(name = "Admins")
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    Controller validateEmailController;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("me/session")
    @RolesAllowed("Admin")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "204", description = "JWT valido")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "401", description = "JWT invalido")
    @APIResponse(responseCode = "404", description = "Admin Not Found")
    @APIResponse(responseCode = "500", description = "Não foi possivel validar o jwt")
    @Operation(summary = "Check if jwt passed through auth bearer is a valid admin")
    public Response validateAdminJwt() throws Exception {
        return validateEmailController.handle(jwt);
    }
}