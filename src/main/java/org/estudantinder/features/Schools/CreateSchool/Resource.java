package org.estudantinder.features.Schools.CreateSchool;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.features.Schools.CreateSchool.DTO.SchoolDTO;

@Path("school")
@Tag(name = "School")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    Controller createSchoolController;

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "409", description = "School Already Exists")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Create a new School")
    public Response createSchool(SchoolDTO data) throws Exception {
        return createSchoolController.handle(data);
    }
}