package org.estudantinder.features.Report.DeleteReport;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("report")
@Tag(name = "Report")
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    Controller banReportedUserController;

    @DELETE
    @Path("{reportId}")
    @Transactional
    @RolesAllowed("Admin")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "200", description = "Report suceessfully deleted")
    @APIResponse(responseCode = "404", description = "Report id not found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Delete a report")
    public Response banReportedUser(@PathParam("reportId") Long reportId) throws Exception {
        return banReportedUserController.handle(reportId);
    }
}