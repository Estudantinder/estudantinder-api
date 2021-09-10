package org.estudantinder.features.Admins.CreateAdmin;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
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
import org.estudantinder.features.Admins.CreateAdmin.DTO.AdminDTO;

@Path("admins")
@Tag(name = "Admins")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    Controller createAdminController;

    @Inject
    JsonWebToken jwt;

    @POST
    @Transactional
    @RolesAllowed("Admin")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "409", description = "Email Already In Use")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Create a new Admin")
    public Response createAdmin(@Valid AdminDTO data) throws Exception {
        return createAdminController.handle(data, jwt);
    }
}