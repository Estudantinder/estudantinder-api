package org.estudantinder.features.Users.ImageUpload;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
import org.estudantinder.repositories.UsersRepository;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;


@Path("users")
@Tag(name = "Users")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsersRepository usersRepository;

    @Inject
    Controller imageUploadController;

    @POST
    @RolesAllowed("User")
    @SecurityRequirement(name = "jwt")
    @Path("imageUpload")
    @Transactional
    @APIResponse(responseCode = "200", description = "User's photos sucessfully uploaded")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "404", description = "Jwt id not found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Upload user's photos")
    public Response sendFile(@Context SecurityContext ctx, @NotNull @MultipartForm DTO data) throws Exception {
        return imageUploadController.handle(jwt, data);
    }
  
}
