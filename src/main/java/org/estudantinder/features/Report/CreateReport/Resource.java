package org.estudantinder.features.Report.CreateReport;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {

    @Inject
    Controller createReportController;

    @POST
    @Path("{reportedUserId}")
    @Transactional
    @RolesAllowed("User")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "201", description = "Report created")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "404", description = "Student not found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Report a student")
    public Response createSchool(@PathParam("reportedUserId") Long reportedUserId, CreateReportDTO reportData) throws Exception {
        return createReportController.handle(reportData, reportedUserId);
    }
}