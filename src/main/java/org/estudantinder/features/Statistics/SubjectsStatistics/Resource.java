package org.estudantinder.features.Statistics.SubjectsStatistics;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("statistics")
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
@Tag(name = "Statistics", description = "App statistics")
public class Resource {

    @Inject 
    Controller showNumberOfUsersInSubjectController;

    @GET
    @RolesAllowed("Admin")
    @SecurityRequirement(name = "jwt")
    @Path("subjects")
    @Operation(summary = "Get number of users in each subject(Both searching and using)")
    public Response getSubjectStatistics() throws Exception {
        return showNumberOfUsersInSubjectController.handle();
    }

}
