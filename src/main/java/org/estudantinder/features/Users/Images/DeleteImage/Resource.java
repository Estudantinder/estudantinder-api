package org.estudantinder.features.Users.Images.DeleteImage;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    JsonWebToken jwt;

    @Inject
    Controller deleteImageController;

    @DELETE
    @RolesAllowed("User")
    @SecurityRequirement(name = "jwt")
    @Path("deleteImage/{imageIndex}")
    @Transactional
    @APIResponse(responseCode = "200", description = "User photo sucessfully deleted")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "404", description = "Jwt id not found || ImageIndex not found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Delete a user's image")
    public Response sendFile(@PathParam("imageIndex") Integer imageIndex) throws Exception {
        return deleteImageController.handle(jwt, imageIndex);
    }
  
}
